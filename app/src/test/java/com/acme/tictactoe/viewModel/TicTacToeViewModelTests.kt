package com.acme.tictactoe.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.acme.tictactoe.model.ActiveGameState
import com.acme.tictactoe.model.Mode
import com.acme.tictactoe.model.Symbol
import com.acme.tictactoe.viewmodel.TicTacToeViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.coroutines.ContinuationInterceptor


/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
@ExperimentalCoroutinesApi
class TicTacToeViewModelTests {

    val testDispatcher = TestCoroutineDispatcher()

    // Run tasks synchronously
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    // Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setUp() {
        viewModel = TicTacToeViewModel(testDispatcher)
    }

   /**
     * This test will simulate and verify x is the winner.
     *
     * X | X | X
     * O |   |
     *   | O |
     */
    @Test
    fun test3inRowAcrossTopForX() {
       clickAndAssertValueAt(0, 0)
       clickAndAssertValueAt(1, 0)
       clickAndAssertValueAt(0, 1)
       clickAndAssertValueAt(2, 1)
       clickAndAssertValueAt(0, 2, winner = Symbol.X)
   }

    /**
     * This test will simulate and verify o is the winner.
     *
     * O | X | X
     *   | O |
     *   | X | O
     */
    @Test
    fun test3inRowDiagonalFromTopLeftToBottomForO() {
        clickAndAssertValueAt(0, 1)
        clickAndAssertValueAt(0, 0)
        clickAndAssertValueAt(2, 1)
        clickAndAssertValueAt(1, 1)
        clickAndAssertValueAt(0, 2)
        clickAndAssertValueAt(2, 2, winner = Symbol.O)
    }

    private fun clickAndAssertValueAt(row: Int, col: Int, winner: Symbol? = null) {

        testDispatcher.pauseDispatcher()

        viewModel.gameStateLiveData.observeForTesting {

            testDispatcher.resumeDispatcher()
            viewModel.onClickedCellAt(row, col)

            viewModel.gameStateLiveData.value?.let { activeGameState ->
                if(winner != null) {
                    assertThat(activeGameState.winner?.symbol, IsEqual(winner))
                    assert(activeGameState.mode == Mode.Finished)
                } else {
                    assertNull(activeGameState.winner)
                    assert(activeGameState.mode == Mode.InProgress)
                }
            }
        }
    }
}

/**
 * Taken from https://gist.github.com/manuelvicnt/049ce057fa6b5e5c785ec9fff7c22a7c
 */

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}


/**
 * Taken from https://github.com/android/architecture-components-samples/blob/master/
 *             LiveDataSample/app/src/test/java/com/android/example/
 *             livedatabuilder/LiveDataViewModelTest.kt
 */
fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    afterObserve.invoke()

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}