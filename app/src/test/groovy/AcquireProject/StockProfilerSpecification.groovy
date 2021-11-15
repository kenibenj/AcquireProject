/**
 *
 *
 * MIT License
 *
 * Copyright (c) 2021 Michael Collier, Emily Elzinga, Benjamin Keninger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Testing for the stock profiler class
 * @author Emily Elzinga
 * @since 11/14/2021
 */

package AcquireProject

import spock.lang.Specification


class StockProfilerSpecification extends Specification {
    def stockprofiler
    def american
    def worldwide
    def t1,t2,t3,t4,t5,t6
    def setup(){
        stockprofiler = StockProfiler.instance()

        t1 = Arrays.asList(1,2);
        t2 = Arrays.asList(3,5);
        t3 = Arrays.asList(7,7);
        t4 = Arrays.asList(4,7);
        t5 = Arrays.asList(9,6);
        t6 = Arrays.asList(1,4);

        american = new HotelChain("American",1)
        worldwide = new HotelChain("Worldwide",0)
        stockprofiler.addChain(american.getName())
        stockprofiler.addChain(worldwide.getName())
    }

    /**
     * makes a map of the number of stocks bob has in each chain
     */
    def "player-bob-profile"(){
        def s1 = new Stock("Worldwide")
        def s2 = new Stock("Worldwide")
        def s3 = new Stock("Worldwide")
        def s4 = new Stock("Worldwide")
        def s5 = new Stock("Worldwide")
        def s6 = new Stock("Worldwide")
        def s7 = new Stock("Worldwide")


        def p1 = new Player("bob",Arrays.asList(t1,t2,t3,t4,t5,t6))
        p1.addStock(s1)
        p1.addStock(s2)
        p1.addStock(s3)
        p1.addStock(s4)
        p1.addStock(s5)
        p1.addStock(s6)
        p1.addStock(s7)

        def map = new HashMap();
        map.put("American",0)
        map.put("Worldwide",7)

        expect:
        stockprofiler.createPlayerProfile(p1) == map
    }


    /**
     * makes a map of each player's stock stock for sackson
     */
    def "chain-sackson-profile"(){

        def sackson = new HotelChain("Sackson",0)
        stockprofiler.addChain(sackson.getName())
        def p1 = new Player("bob",  Arrays.asList(t1,t2,t3,t4,t5,t6))
        sackson.giveStock(p1);
        sackson.giveStock(p1);
        sackson.giveStock(p1);

        def map = new HashMap()
        map.put(p1,3)

        expect:
        stockprofiler.createChainProfile(sackson) == map
    }
}