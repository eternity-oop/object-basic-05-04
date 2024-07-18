package org.eternity.reservation.console;

import org.eternity.reservation.domain.Reservation;
import org.eternity.reservation.service.CustomerDAO;
import org.eternity.reservation.service.ReservationDAO;
import org.eternity.reservation.service.ReservationService;
import org.eternity.reservation.service.ScreeningDAO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class ReservationConsole {
    private TransactionTemplate transactionTemplate;
    private CustomerDAO customerDAO;
    private ScreeningDAO screeningDAO;
    private ReservationDAO reservationDAO;
    private ReservationService reservationService;

    public ReservationConsole(TransactionTemplate transactionTemplate,
                              CustomerDAO customerDAO,
                              ScreeningDAO screeningDAO,
                              ReservationDAO reservationDA,
                              ReservationService reservationService) {
        this.transactionTemplate = transactionTemplate;
        this.customerDAO = customerDAO;
        this.screeningDAO = screeningDAO;
        this.reservationDAO = reservationDAO;
        this.reservationService = reservationService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("고객 번호를 입력하세요: ");
            Long customerId = scanner.nextLong();

            System.out.println("상영 번호를 입력하세요: ");
            Long screeningId = scanner.nextLong();

            System.out.println("관객 수를 입력하세요: ");
            Integer audienceCount = scanner.nextInt();

            Reservation result = reservationService.reserveScreening(customerId, screeningId, audienceCount);

            System.out.println(MessageFormat.format(
                    "고객 번호: {0}, 고객 이름: {1}, 상영 번호: {2}, 영화 번호: {3}, 영화 제목: {4}, 예약자수: {5}, 요금: {6}, 상영시간: {7}",
                    result.getCustomer().getId(),
                    result.getCustomer().getName(),
                    result.getScreening().getId(),
                    result.getScreening().getMovie().getId(),
                    result.getScreening().getMovie().getTitle(),
                    result.getAudienceCount(),
                    result.getFee(),
                    result.getScreening().getStartTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))));
        }
    }

//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        while(true) {
//            System.out.println("고객 번호를 입력하세요: ");
//            Long customerId = scanner.nextLong();
//
//            System.out.println("상영 번호를 입력하세요: ");
//            Long screeningId = scanner.nextLong();
//
//            System.out.println("관객 수를 입력하세요: ");
//            Integer audienceCount = scanner.nextInt();
//
//            Reservation result = transactionTemplate.execute((status) -> {
//                Customer customer = customerDAO.find(customerId);
//                Screening screening = screeningDAO.find(screeningId);
//
//                Reservation reservation = screening.reserve(customer, audienceCount);
//
//                reservationDAO.save(reservation);
//
//                return reservation;
//            });
//
//            System.out.println(MessageFormat.format(
//                    "고객 번호: {0}, 고객 이름: {1}, 상영 번호: {2}, 영화 번호: {3}, 영화 제목: {4}, 예약자수: {5}, 요금: {6}, 상영시간: {7}",
//                    result.getCustomer().getId(),
//                    result.getCustomer().getName(),
//                    result.getScreening().getId(),
//                    result.getScreening().getMovie().getId(),
//                    result.getScreening().getMovie().getTitle(),
//                    result.getAudienceCount(),
//                    result.getFee(),
//                    result.getScreening().getStartTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))));
//        }
//    }
}
