package com.ynz.finance.exploreyahoo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
class SingleStockTest {

    @Test
    void testGetStockFromYahoo() throws IOException {
        Stock stock = YahooFinance.get("TSLA");
        assertNotNull(stock);
        log.info("stock name: " + stock.getName());
        log.info("stock quote: " + stock.getQuote());
        log.info("stock history" + stock.getHistory());
    }

    @Test
    void testGetStockHistory() throws IOException {
        Stock tesla = YahooFinance.get("TSLA", true);
        assertNotNull(tesla);
        tesla.getHistory().stream().forEach(historicalQuote -> log.info(historicalQuote.toString()));
    }

    @Test
    void testGetStockHistoryOneYearDaily() throws IOException {
        Stock tesla = YahooFinance.get("TSLA", Interval.DAILY);
        assertNotNull(tesla);
        tesla.getHistory().stream().forEach(historicalQuote -> log.info(historicalQuote.toString()));
    }

    @Test
    void testGetStockHistoryHalfYearDaily() throws IOException {
        LocalDate halfYearEarly = LocalDate.now().minusMonths(6L);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(halfYearEarly.getYear(), halfYearEarly.getMonth().getValue(), halfYearEarly.getDayOfMonth());

        Stock tesla = YahooFinance.get("TSLA", calendar, Interval.DAILY);
        assertNotNull(tesla);
        tesla.getHistory().stream().forEach(historicalQuote -> log.info(historicalQuote.toString()));
    }

}