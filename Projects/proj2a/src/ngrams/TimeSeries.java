package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Collections;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 * @author stoicneko
 * @since 2025.11.9
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // 直接putAll, 不需要创建copy实例变量,
        // 构造函数本身的目的就是：初始化 this。
        this.putAll(ts.subMap(startYear, true, endYear, true));
    }

    /**
     *  Returns all years for this time series in ascending order/升序.
     */
    public List<Integer> years() {
        return new ArrayList<>(this.keySet());
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        List<Double> returnList = new ArrayList<>();
        for (Integer i : this.years()) {
            returnList.add(this.get(i));
        }
        return returnList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // 不会返回null, 只会返回空List
        if (this.isEmpty() && ts.isEmpty()) {
            return new TimeSeries();
        }
        TimeSeries returnTs = new TimeSeries();

        for (Integer year : this.years()) {
            if (ts.containsKey(year)) {
                double sum = this.get(year) + ts.get(year);
                returnTs.put(year, sum);
            } else {
                returnTs.put(year, this.get(year));
            }
        }

        for (Integer year : ts.years()) {
            if (!returnTs.containsKey(year)) {
                returnTs.put(year, ts.get(year));
            }
        }

        return returnTs;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries returnTs = new TimeSeries();
        for (Integer year : this.years()) {
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException("Cannot divide: year " + year + " not found in divisor TimeSeries");
            }
            returnTs.put(year, this.get(year) / ts.get(year));
        }
        return returnTs;
    }
}
