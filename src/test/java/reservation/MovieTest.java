package reservation;

import org.eternity.generic.Money;
import org.eternity.reservation.domain.*;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {
    @Test
    public void 비율할인정책_계산하기() {
        // given
        Movie movie = new Movie(
                "한산",
                Money.wons(10_000L),
                new PercentDiscountPolicy(
                        0.1,
                        new SequenceCondition(1),
                        new SequenceCondition(3),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 10))));

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024,12,11, 18, 0));

        // when
        Money fee = movie.calculateFee(screening);

        // then
        assertEquals(Money.wons(9000L), fee);
    }

    @Test
    public void 금액할인정책_계산하기() {
        // given
        Movie movie = new Movie(
                "한산",
                Money.wons(10_000L),
                new AmountDiscountPolicy(
                        Money.wons(1000L),
                        new SequenceCondition(1),
                        new SequenceCondition(3),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 10))));

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024,12,11, 18, 0));

        // when
        Money fee = movie.calculateFee(screening);

        // then
        assertEquals(Money.wons(9000L), fee);
    }

    @Test
    public void 비할인정책_계산하기() {
        // given
        Movie movie = new Movie(
                "한산",
                Money.wons(10_000L),
                new NoneDiscountPolicy());

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024,12,11, 18, 0));

        // when
        Money fee = movie.calculateFee(screening);

        // then
        assertEquals(Money.wons(10000L), fee);
    }
}