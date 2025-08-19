package Travel;
import java.util.Scanner;


public class TravelApp {
    public static void main(String[] args) {
//        TravelDao doa =  new TravelDao();
//        ArrayList<TravelVo> rs = null;
//
//        rs = doa.executeQuery("select * from travel where no between 1 and 10");
//
//        for (TravelVo r : rs) {
//            String district = r.getDistrict();
//            String title = r.getTitle();
//            String description = r.getDescription();
//            String address = r.getAddress();
//            String phone = r.getPhone();
//            System.out.println(district + " " + title + " " + description + " " + address + " " + phone);
//        }



        try (Scanner scanner = new Scanner(System.in)) { // Scanner를 try-with-resources로 선언하여 자동으로 닫히게 설정
            TravelService ts = new  TravelService(scanner);
            while (true) { // 무한 루프를 사용하여 메뉴 반복 출력
                // 메뉴 출력
                System.out.println("\n=== TravelApp Menu ===");
                System.out.println("1. 전체목록(테이블 형태로 출력)");
                System.out.println("2. 권역별 관광지");
                System.out.println("3. 검색");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt(); // 사용자로부터 메뉴 선택 입력 받음
                scanner.nextLine(); // 버퍼에 남아있는 줄바꿈 문자 제거

                switch (choice) { // 선택한 옵션에 따라 동작 분기
                    case 1:
                        ts.printAllPage();
                        break;
                    case 2:
                       ts.printArea();
                        break;
                    case 3:
                        ts.printSearchResult();
                        break;
                    case 4:
                        System.out.println("Exiting..."); // 종료 메시지 출력
                        return; // 프로그램 종료
                    default:
                        System.out.println("Invalid choice. Try again."); // 잘못된 입력 메시지 출력
                }
            }
        }
    }
}
