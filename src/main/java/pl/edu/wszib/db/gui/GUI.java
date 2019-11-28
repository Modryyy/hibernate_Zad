package pl.edu.wszib.db.gui;

import pl.edu.wszib.db.DBConnector;
import pl.edu.wszib.model.Pytanie;

import java.util.List;
import java.util.Scanner;

public class GUI {
        public static void Menu(){
    Scanner scanner = new Scanner(System.in);
            System.out.println("1.Dodaj Pytanie \n2.Usun Pytanie \n3.Odczytaj Pytanie \n4.Edytuj Całe Pytanie \n5.Edytuj tylko Nagrodę\n6.Edytuj tylko Pytanie\n7.Edytuj tylko odpowiedzi\n8.Edytuj tylko prawidłową odpowiedź\n9.Wyświetl wszystkie pytania\n10.Wyjdź");
    int wybor = scanner.nextInt();


    switch (wybor) {
        case 1:
            AddQuestion();
            Menu();
            break;
        case 2:
            RemoveQuestion();
            Menu();
            break;
        case 3:
            OneQuestion();
            Menu();
            break;
        case 4:

            ChangeWholeQuestion();
            Menu();
            break;
        case 5:
            ChangeQuestionReward();
            Menu();
            break;
        case 6:
            ChangeQuestion();
            Menu();
            break;
        case 7:
            ChangeAnswer();
            Menu();
            break;
        case 8:
            ChangeCorrectAnswer();
            Menu();
            break;
        case 9:
            getAllQuestions();
            Menu();
            break;
        case 10:
          System.exit(0);
            break;
        default:
            Menu();
            break;
    }

    }
    public static void OneQuestion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id pytania ktore chcesz odczytac: ");
        int wyborIdPytania = scanner.nextInt();
        while(DBConnector.getPytanie(wyborIdPytania)==null) {
            System.out.println("Podaj jeszcze raz id pytania ktore chcesz odczytac");
            wyborIdPytania = scanner.nextInt();

        }
        Pytanie pytanie = DBConnector.getPytanie(wyborIdPytania);
        System.out.println(pytanie);

    }

    public static void AddQuestion(){
        Pytanie pytanie = new Pytanie();
        pytanie.setNagroda(AddOrUpdateReward());
        pytanie.setPytanie(AddOrUpdateQuestion());
        pytanie.setOdpowiedzi(AddOrUpdateAnswer());
        pytanie.setPrawidlowaOdpowiedz(AddOrUpdateCorrectAnswer());
        System.out.println(pytanie);
        DBConnector.addQuestion(pytanie);



    }

    public static int ID(){
      //  Pytanie pytanie = new Pytanie();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj idPytania ktorego chcesz aktualizowac: ");

        int idAktualizacja = scanner.nextInt();
        while (DBConnector.getPytanie(idAktualizacja) == null) {
            System.out.println("Nie ma takiego pytania! Podaj jeszcze raz idPytania:");
            idAktualizacja = scanner.nextInt();


            System.out.println("Powrot do menu = -10");
            if (idAktualizacja == -10) {
                Menu();
            }

        }

        System.out.println(idAktualizacja);
        return idAktualizacja;
    }


    public static void RemoveQuestion() {

        System.out.println("Podaj idPytania ktorego chcesz usunac.");
        Scanner scanner = new Scanner(System.in);
        int idUsuwanegoPytania = scanner.nextInt();

        while (DBConnector.getPytanie(idUsuwanegoPytania) == null) {
            System.out.println("Nie ma takiego pytania! Podaj jeszcze raz idPytania:");
            idUsuwanegoPytania = scanner.nextInt();
            System.out.println("Powrot do menu = -10");
            if (idUsuwanegoPytania == -10) {
                Menu();

            }
        }

            DBConnector.removeQuestion(idUsuwanegoPytania);

        }


    public static int AddOrUpdateReward(){
     //   Pytanie pytanie = new Pytanie();
        Scanner scanner = new Scanner(System.in);
        boolean prawidlowyWybor = false;


            System.out.println("Wybierz nagrode za pytanie!");
            System.out.println("500, 1000, 2000, 5000, 10000, 20000, 40000, 75000, 125000, 250000, 500000, 1000000");

            while(prawidlowyWybor == false) {
                String kwota = scanner.nextLine();
                try {
                    if (kwota.equals("500") || kwota.equals("1000") || kwota.equals("2000") || kwota.equals("5000") || kwota.equals("10000") ||
                            kwota.equals("20000") || kwota.equals("40000") || kwota.equals("75000") || kwota.equals("125000") || kwota.equals("250000") ||
                            kwota.equals("500000") | kwota.equals("1000000")) {


                        return Integer.parseInt(kwota);


                    }
                    else {System.out.println("podaj prawidlowa wartosc");}
                }
                catch (NumberFormatException e) {
                }
            }
            return 0;
    }

        public static String AddOrUpdateQuestion(){


            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj tresc Pytania");
            String pyt = scanner.nextLine();

            return pyt;

        }
    public static String AddOrUpdateAnswer() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj proponowe odpowiedzi Pytania");
        System.out.println("Odpowiedz A: ");
        String odpA = scanner.nextLine();
        System.out.println("Odpowiedz B: ");
        String odpB = scanner.nextLine();
        System.out.println("Odpowiedz C: ");
        String odpC = scanner.nextLine();
        System.out.println("Odpowiedz D: ");
        String odpD = scanner.nextLine();

        String Odpowiedz = ("a)" + odpA + "\t" + "b)" + odpB + "\t" + "c)" + odpC + "\t" + "d)" + odpD);

 return Odpowiedz;
    }

    public static String AddOrUpdateCorrectAnswer() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ktora odp jest prawidlowa? ");
        System.out.println("a,b,c czy d");


        boolean prawidlowaodp1 = false;
        while(prawidlowaodp1 == false) {
            String PrawidlowaOdp = scanner.nextLine();
            if (PrawidlowaOdp.equals("a")|| PrawidlowaOdp.equals("b")||PrawidlowaOdp.equals("c")||PrawidlowaOdp.equals("d")) {

                System.out.println(PrawidlowaOdp);
                return PrawidlowaOdp;

            }
            System.out.println("wpisz poprawnie odpowiedz (tylko 1 litera: a,b,c lub d)");

        }

        System.out.println("error");
        return null;
    }

    public static void ChangeWholeQuestion(){
Pytanie pytanie = new Pytanie();
int id = ID();
        pytanie.setIdPytania(id);
        pytanie.setNagroda(AddOrUpdateReward());
        pytanie.setPytanie(AddOrUpdateQuestion());
        pytanie.setOdpowiedzi(AddOrUpdateAnswer());
        pytanie.setPrawidlowaOdpowiedz(AddOrUpdateCorrectAnswer());
        System.out.println(pytanie);

            DBConnector.updateWholeQuestion(id, pytanie);


    }
    public static void ChangeQuestionReward(){
        Pytanie pytanie = new Pytanie();
        int id = ID();
        pytanie.setIdPytania(id);
        pytanie.setNagroda(AddOrUpdateReward());
        DBConnector.updateQuestionReward(id, pytanie);
    }

    public static void ChangeQuestion(){
        Pytanie pytanie = new Pytanie();
        int id = ID();
        pytanie.setIdPytania(id);
        pytanie.setPytanie(AddOrUpdateQuestion());
        DBConnector.updateQuestion(id, pytanie);
    }
    public static void ChangeAnswer(){
        Pytanie pytanie = new Pytanie();
        int id = ID();
        pytanie.setIdPytania(id);
        pytanie.setOdpowiedzi(AddOrUpdateAnswer());
        DBConnector.updateAnswer(id, pytanie);
    }
    public static void ChangeCorrectAnswer(){
        Pytanie pytanie = new Pytanie();
        int id = ID();
        pytanie.setIdPytania(id);
        pytanie.setPrawidlowaOdpowiedz(AddOrUpdateCorrectAnswer());
        DBConnector.updateCorrectAnswer(id, pytanie);
    }

    public static void getAllQuestions(){
    List<Pytanie> allQuestions = DBConnector.getAllQuestions();
        System.out.println("LISTA PYTAN:");
        for(Pytanie tempquest : allQuestions) {
        System.out.println(tempquest);
    }
        System.out.println();  }
}

