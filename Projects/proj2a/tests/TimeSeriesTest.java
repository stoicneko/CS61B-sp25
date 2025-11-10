import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
        expectedYears.add(1994);
        expectedYears.add(1995);

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(600.0);
        expectedTotal.add(500.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void testBasic() {
        TimeSeries catPopulation = new TimeSeries();

        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
//        expectedYears.add(1994);

//        assertThat(catPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(200.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(catPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }

        TimeSeries dogPopulation = new TimeSeries(catPopulation, 1991, 1992);
        assertThat(dogPopulation.years()).isEqualTo(expectedYears);
    }




    @Test
    public void testDividedBy() {
        TimeSeries revenue = new TimeSeries();
        revenue.put(2000, 100.0);
        revenue.put(2001, 200.0);
        revenue.put(2002, 300.0);

        TimeSeries cost = new TimeSeries();
        cost.put(2000, 50.0);
        cost.put(2001, 100.0);
        cost.put(2002, 150.0);
        cost.put(2003, 400.0);  // 多余年份，应被忽略

        TimeSeries ratio = revenue.dividedBy(cost);

        // 预期年份：和 revenue 一致
        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(2000);
        expectedYears.add(2001);
        expectedYears.add(2002);

        assertThat(ratio.years()).isEqualTo(expectedYears);

        // 预期结果：100/50=2, 200/100=2, 300/150=2
        List<Double> expectedRatios = new ArrayList<>();
        expectedRatios.add(2.0);
        expectedRatios.add(2.0);
        expectedRatios.add(2.0);

        for (int i = 0; i < expectedRatios.size(); i++) {
            assertThat(ratio.data().get(i)).isWithin(1E-10).of(expectedRatios.get(i));
        }
    }

    @Test
    public void testDividedByMissingYearThrowsException() {
        TimeSeries a = new TimeSeries();
        a.put(2010, 10.0);
        a.put(2011, 20.0);

        TimeSeries b = new TimeSeries();
        b.put(2011, 5.0); // 缺少 2010

        assertThrows(IllegalArgumentException.class, () -> {
            a.dividedBy(b);
        });
    }
}