package PL;

import Entity.*;
import BLL.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Menu {
    private Games games;
    private Stadiums stadiums;
    private Players players;
    private Scanner scanner;
    public Menu() {
        try {
            this.games = new Games();
            this.stadiums = new Stadiums();
            this.players = new Players();
            this.scanner = new Scanner(System.in);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void MainMenu() {
        System.out.println();
        System.out.println();
        System.out.println("                      Головне меню");
        System.out.println("Введіть \"1\", щоб відкрити меню управління гравцями.");
        System.out.println("Введіть \"2\", щоб відкрити меню управління іграми.");
        System.out.println("Введіть \"3\", щоб відкрити меню управління стадіонами.");
        System.out.println("Введіть \"4\", щоб відкрити меню пошуку.");
        System.out.println("Введіть \"0\", щоб закрити програмну систему.");
        System.out.println();
        System.out.print(">");
        try {
            int key = scanner.nextInt();
            while (key != 1 && key != 2 && key != 3 && key != 4 && key != 0) {
                System.out.print("Введіть іншу цифру >");
                key = scanner.nextInt();
            }
            switch (key) {
                case 1:
                    PlayersMenu();
                    return;
                case 2:
                    GamesMenu();
                    return;
                case 3:
                    StadiumsMenu();
                    return;
                case 4:
                    FindMenu();
                    return;
                case 0:
                    try {
                        this.games.Serialize();
                        this.stadiums.Serialize();;
                        this.players.Serialize();
                        return;
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Неправильні дані.");
            scanner.nextLine();
            MainMenu();
            return;
        }
    }
    private void GamesMenu() {
        System.out.println();
        System.out.println();
        System.out.println("              Меню управління іграми");
        System.out.println("Введіть \"1\", щоб додати гру.");
        System.out.println("Введіть \"2\", щоб видалити гру.");
        System.out.println("Введіть \"3\", щоб змінити дані про гру (додати гравця).");
        System.out.println("Введіть \"4\", щоб змінити дані про гру (видалити гравця).");
        System.out.println("Введіть \"5\", щоб змінити дані про гру (змінити дату проведення).");
        System.out.println("Введіть \"6\", щоб змінити дані про гру (змінити місце проведення).");
        System.out.println("Введіть \"7\", щоб змінити дані про гру (змінити кількість глядачів).");
        System.out.println("Введіть \"8\", щоб переглянути дані про гру.");
        System.out.println("Введіть \"9\", щоб переглянути весь список ігор.");
        System.out.println("Введіть \"10\", щоб відсортувати ігри за датою проведення.");
        System.out.println("Введіть \"11\", щоб відсортувати ігри за результатом.");
        System.out.println("Введіть \"0\", щоб повернутися в головне меню.");
        System.out.println();
        System.out.print(">");
        try {
            int key = scanner.nextInt();
            while (key != 1 && key != 2 && key != 3 && key != 4 && key != 5
                    && key != 6 && key != 7 && key != 8 && key != 9 && key != 10
                    && key != 11 && key != 0) {
                System.out.print("Введіть іншу цифру >");
                key = scanner.nextInt();
            }
            scanner.nextLine();
            String _team_1, _team_2, _player, _stadium;
            int _day, _month, _year, _team, _new_day, _new_month, _new_year, _viewers, _team_1_score, _team_2_score;
            switch (key) {
                case 1:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть кількість гравців в першій команді >");
                        int tmp = scanner.nextInt();
                        scanner.nextLine();
                        ArrayList<String> _team_1_players = new ArrayList<>();
                        for (int i = 0; i < tmp; i++) {
                            System.out.println(String.format("Введіть ініціали %d гравця >", i + 1));
                            _team_1_players.add(scanner.nextLine());
                        }
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть кількість гравців в другій команді >");
                        tmp = scanner.nextInt();
                        scanner.nextLine();
                        ArrayList<String> _team_2_players = new ArrayList<>();
                        for (int i = 0; i < tmp; i++) {
                            System.out.println(String.format("Введіть ініціали %d гравця >", i + 1));
                            _team_2_players.add(scanner.nextLine());
                        }
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введіть назву стадіону >");
                        _stadium = scanner.nextLine();
                        System.out.println("Введіть кількість глядачів >");
                        _viewers = scanner.nextInt();
                        int day = Calendar.getInstance().get(5),
                                month = Calendar.getInstance().get(2) + 1,
                                year = Calendar.getInstance().get(1);
                        if (_year < year)
                        {
                            scanner.nextLine();
                            System.out.println("Введіть кількість голів, забитих 1-ю командою >");
                            _team_1_score = scanner.nextInt();
                            System.out.println("Введіть кількість голів, забитих 2-ю командою >");
                            _team_2_score = scanner.nextInt();
                            this.games.AddGame(new Game(_team_1, _team_2, _team_1_players, _team_2_players,
                                    _day, _month, _year, this.stadiums.GetStadium(_stadium), _viewers,
                                    _team_1_score, _team_2_score));
                        }
                        else if (_year == year && _month < month)
                        {
                            scanner.nextLine();
                            System.out.println("Введіть кількість голів, забитих 1-ю командою >");
                            _team_1_score = scanner.nextInt();
                            System.out.println("Введіть кількість голів, забитих 2-ю командою >");
                            _team_2_score = scanner.nextInt();
                            this.games.AddGame(new Game(_team_1, _team_2, _team_1_players, _team_2_players,
                                    _day, _month, _year, this.stadiums.GetStadium(_stadium), _viewers,
                                    _team_1_score, _team_2_score));
                        }
                        else if (_year == year && _month == month && _day < day)
                        {
                            scanner.nextLine();
                            System.out.println("Введіть кількість голів, забитих 1-ю командою >");
                            _team_1_score = scanner.nextInt();
                            System.out.println("Введіть кількість голів, забитих 2-ю командою >");
                            _team_2_score = scanner.nextInt();
                            this.games.AddGame(new Game(_team_1, _team_2, _team_1_players, _team_2_players,
                                    _day, _month, _year, this.stadiums.GetStadium(_stadium), _viewers,
                                    _team_1_score, _team_2_score));
                        }
                        else
                        {
                            this.games.AddGame(new Game(_team_1, _team_2, _team_1_players, _team_2_players,
                                    _day, _month, _year, this.stadiums.GetStadium(_stadium), _viewers));
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 2:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        this.games.RemoveGame(_team_1, _team_2, _day, _month, _year);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 3:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введіть ініціали гравця >");
                        _player = scanner.nextLine();
                        System.out.println("Введіть номер команди >");
                        _team = scanner.nextInt();
                        this.games.AddPlayer(_team_1, _team_2, _day, _month, _year, _player, _team);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 4:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введіть ініціали гравця >");
                        _player = scanner.nextLine();
                        this.games.RemovePlayer(_team_1, _team_2, _day, _month, _year, _player);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 5:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть старий день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть старий місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть старий рік проведення матчу >");
                        _year = scanner.nextInt();
                        System.out.println("Введіть новий день проведення матчу >");
                        _new_day = scanner.nextInt();
                        System.out.println("Введіть новий місяць проведення матчу >");
                        _new_month = scanner.nextInt();
                        System.out.println("Введіть новий рік проведення матчу >");
                        _new_year = scanner.nextInt();
                        this.games.ChangeDate(_team_1, _team_2, _day, _month, _year, _new_day, _new_month, _new_year);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 6:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введіть назву стадіону >");
                        _stadium = scanner.nextLine();
                        this.games.ChangeStadium(_team_1, _team_2, _day, _month, _year, _stadium, this.stadiums);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 7:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        System.out.println("Введіть кількість глядачів >");
                        _viewers = scanner.nextInt();
                        this.games.ChangeViewers(_team_1, _team_2, _day, _month, _year, _viewers);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 8:
                    try {
                        System.out.println("Введіть назву першої команди >");
                        _team_1 = scanner.nextLine();
                        System.out.println("Введіть назву другої команди >");
                        _team_2 = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        for (String line : this.games.SearchGame(_team_1, _team_2, _day, _month, _year).GetFullInfo()) {
                            System.out.println(line);
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 9:
                    try {
                        GamesTitle();
                        int j = 0;
                        Game[] _games = this.games.GetGames();
                        while (_games[j] != null) {
                            System.out.println(_games[j].GetShortInfo());
                            j++;
                        }
                        System.out.println("|--------------------------------|-----------|---------------|------------------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    GamesMenu();
                    return;
                case 10:
                    this.games.SortByDate();
                    GamesMenu();
                    break;
                case 11:
                    this.games.SortByResult();
                    GamesMenu();
                    break;
                case 0:
                    MainMenu();
                    return;
                default:
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Неправильні дані.");
            scanner.nextLine();
            GamesMenu();
            return;
        }
    }
    private void StadiumsMenu() {
        System.out.println();
        System.out.println();
        System.out.println("              Меню управління стадіонами");
        System.out.println("Введіть \"1\", щоб додати стадіон.");
        System.out.println("Введіть \"2\", щоб видалити стадіон.");
        System.out.println("Введіть \"3\", щоб змінити дані про стадіон (кількість місць).");
        System.out.println("Введіть \"4\", щоб змінити дані про стадіон (ціну за місце).");
        System.out.println("Введіть \"5\", щоб переглянути інформацію про стадіон (кількість місць та ціну за місце).");
        System.out.println("Введіть \"6\", щоб переглянути дати проведення ігор на стадіоні.");
        System.out.println("Введіть \"7\", щоб переглянути весь список стадіонів.");
        System.out.println("Введіть \"0\", щоб повернутися в головне меню.");
        System.out.println();
        System.out.print(">");
        try {
            int key = scanner.nextInt();
            while (key != 1 && key != 2 && key != 3 && key != 4 && key != 5
                    && key != 6 && key != 7 && key != 0) {
                System.out.print("Введіть іншу цифру >");
                key = scanner.nextInt();
            }
            scanner.nextLine();
            String _name;
            int _seats, _price;
            switch (key) {
                case 1:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        System.out.print("Введіть кількість місць >");
                        _seats = scanner.nextInt();
                        System.out.print("Введіть ціну за місце >");
                        _price = scanner.nextInt();
                        this.stadiums.AddStadium(new Stadium(_name, _seats, _price));
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 2:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        this.stadiums.RemoveStadium(_name);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 3:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        System.out.print("Введіть кількість місць >");
                        _seats = scanner.nextInt();
                        this.stadiums.ChangeSeats(_name, _seats);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 4:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        System.out.print("Введіть ціну за місце >");
                        _price = scanner.nextInt();
                        this.stadiums.ChangePrice(_name, _price);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 5:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        StadiumsTitle();
                        System.out.println(this.stadiums.GetStadium(_name).GetStadiumInfo());
                        System.out.println("|---------------|-------|------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 6:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        GamesTitle();
                        for (String line : this.stadiums.GetGames(_name, this.games)) {
                            System.out.println(line);
                        }
                        System.out.println("|--------------------------------|-----------|---------------|------------------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 7:
                    try {
                        StadiumsTitle();
                        for (String stadium : this.stadiums.GetStadiums().keySet()) {
                            System.out.println(this.stadiums.GetStadium(stadium).GetStadiumInfo());
                        }
                        System.out.println("|---------------|-------|------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    StadiumsMenu();
                    return;
                case 0:
                    MainMenu();
                    return;
                default:
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Неправильні дані.");
            scanner.nextLine();
            StadiumsMenu();
            return;
        }
    }
    private void PlayersMenu() {
        System.out.println();
        System.out.println();
        System.out.println("              Меню управління гравцями");
        System.out.println("Введіть \"1\", щоб додати гравця.");
        System.out.println("Введіть \"2\", щоб видалити гравця.");
        System.out.println("Введіть \"3\", щоб змінити дані про гравця (ім'я).");
        System.out.println("Введіть \"4\", щоб змінити дані про гравця (прізвище).");
        System.out.println("Введіть \"5\", щоб змінити дані про гравця (дату народження).");
        System.out.println("Введіть \"6\", щоб змінити дані про гравця (статус гравця).");
        System.out.println("Введіть \"7\", щоб змінити дані про гравця (статус здоров'я).");
        System.out.println("Введіть \"8\", щоб змінити дані про гравця (зарплатню).");
        System.out.println("Введіть \"9\", щоб переглянути інформацію про гравця.");
        System.out.println("Введіть \"10\", щоб переглянути весь список гравців");
        System.out.println("Введіть \"0\", щоб повернутися в головне меню.");
        System.out.println();
        System.out.print(">");
        try {
            int key = scanner.nextInt();
            while (key != 1 && key != 2 && key != 3 && key != 4 && key != 5
                    && key != 6 && key != 7 && key != 8 && key != 9 && key != 10
                    && key != 0) {
                System.out.print("Введіть іншу цифру >");
                key = scanner.nextInt();
            }
            scanner.nextLine();
            String _first_name, _last_name, _play_status, _health_status,
                    _team, _new_data;
            int _day, _month, _year, _salary;
            boolean _status;
            switch (key) {
                case 1:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.println("Введіть день народження гравця >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць народження гравця >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік народження гравця >");
                        _year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введіть статус гравця (\'+\', якщо гравець бере участь в іграх, інакше \'-\') >");
                        _play_status = scanner.nextLine();
                        if (_play_status.equalsIgnoreCase("+")) {
                            _status = true;
                        } else {
                            _status = false;
                        }
                        System.out.print("Введіть статус здоров'я гравця >");
                        _health_status = scanner.nextLine();
                        System.out.println("Введіть зарплатню гравця >");
                        _salary = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введіть команду гравця >");
                        _team = scanner.nextLine();
                        this.players.AddPlayer(new FootballPlayer(_first_name, _last_name,
                                _day, _month, _year, _status, _health_status, _salary, _team));
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 2:
                    System.out.print("Введіть ім'я гравця >");
                    _first_name = scanner.nextLine();
                    System.out.print("Введіть прізвище гравця >");
                    _last_name = scanner.nextLine();
                    try {
                        this.players.RemovePlayer(_first_name, _last_name);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 3:
                    try {
                        System.out.print("Введіть старе ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.print("Введіть нове ім'я гравця >");
                        _new_data = scanner.nextLine();
                        this.players.ChangeFirstName(_first_name, _last_name, _new_data);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 4:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть старе прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.print("Введіть нове прізвище гравця >");
                        _new_data = scanner.nextLine();
                        this.players.ChangeLastName(_first_name, _last_name, _new_data);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 5:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.println("Введіть день народження гравця >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць народження гравця >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік народження гравця >");
                        _year = scanner.nextInt();
                        scanner.nextLine();
                        this.players.ChangeDate(_first_name, _last_name, _day, _month, _year);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 6:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.println("Введіть статус гравця (\'+\', якщо гравець бере участь в іграх, інакше \'-\') >");
                        _play_status = scanner.nextLine();
                        if (_play_status.equalsIgnoreCase("+")) {
                            _status = true;
                        } else {
                            _status = false;
                        }
                        this.players.ChangePlayStatus(_first_name, _last_name, _status);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 7:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.print("Введіть статус здоров'я гравця >");
                        _health_status = scanner.nextLine();
                        this.players.ChangeHealthStatus(_first_name, _last_name, _health_status);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 8:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        System.out.println("Введіть зарплатню гравця >");
                        _salary = scanner.nextInt();
                        this.players.ChangeSalary(_first_name, _last_name, _salary);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 9:
                    try {
                        System.out.print("Введіть ім'я гравця >");
                        _first_name = scanner.nextLine();
                        System.out.print("Введіть прізвище гравця >");
                        _last_name = scanner.nextLine();
                        PlayersTitle();
                        System.out.println(this.players.GetPlayer(_first_name, _last_name).GetPlayerInfo());
                        System.out.println("|---------------|---------------|-----------|------|-----------|-------|---------------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 10:
                    try {
                        PlayersTitle();
                        for (String player : this.players.GetPlayers().keySet()) {
                            System.out.println(this.players.GetPlayerKey(player).GetPlayerInfo());
                        }
                        System.out.println("|---------------|---------------|-----------|------|-----------|-------|---------------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    PlayersMenu();
                    return;
                case 0:
                    MainMenu();
                    return;
                default:
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Неправильні дані.");
            scanner.nextLine();
            PlayersMenu();
            return;
        }
    }
    private void FindMenu() {
        System.out.println();
        System.out.println();
        System.out.println("              Меню пошуку");
        System.out.println("Введіть \"1\", щоб знайти гравця за ім'ям або прізвищем.");
        System.out.println("Введіть \"2\", щоб знайти гру за датою проведення та назвою команди.");
        System.out.println("Введіть \"3\", щоб знайти стадіон за назвою.");
        System.out.println("Введіть \"0\", щоб повернутися в головне меню.");
        System.out.println();
        System.out.print(">");
        try {
            int key = scanner.nextInt();
            while (key != 1 && key != 2 && key != 3 && key != 0) {
                System.out.print("Введіть іншу цифру >");
                key = scanner.nextInt();
            }
            scanner.nextLine();
            String _name;
            int _day, _month, _year;
            switch (key) {
                case 1:
                    try {
                        System.out.print("Введіть ім'я або прізвище гравця >");
                        _name = scanner.nextLine();
                        PlayersTitle();
                        for (Player player : this.players.GetPlayer(_name)) {
                            System.out.println(player.GetPlayerInfo());
                        }
                        System.out.println("|---------------|---------------|-----------|------|-----------|-------|---------------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    FindMenu();
                    return;
                case 2:
                    try {
                        System.out.print("Введіть назву команди >");
                        _name = scanner.nextLine();
                        System.out.println("Введіть день проведення матчу >");
                        _day = scanner.nextInt();
                        System.out.println("Введіть місяць проведення матчу >");
                        _month = scanner.nextInt();
                        System.out.println("Введіть рік проведення матчу >");
                        _year = scanner.nextInt();
                        GamesTitle();
                        for (Game game : this.games.SearchGames(_name, _day, _month, _year)) {
                            System.out.println(game.GetShortInfo());
                        }
                        System.out.println("|--------------------------------|-----------|---------------|------------------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    FindMenu();
                    return;
                case 3:
                    try {
                        System.out.print("Введіть назву стадіона >");
                        _name = scanner.nextLine();
                        StadiumsTitle();
                        System.out.println(this.stadiums.GetStadium(_name).GetStadiumInfo());
                        System.out.println("|---------------|-------|------|");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    FindMenu();
                    return;
                case 0:
                    MainMenu();
                    return;
                default:
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Неправильні дані.");
            scanner.nextLine();
            FindMenu();
            return;
        }
    }
    private static void GamesTitle()
    {
        System.out.println("|--------------------------------|-----------|---------------|------------------|");
        System.out.println("|      Команда 1 -     Команда 2 |   Дата    |    Стадіон    |     Результат    |");
        System.out.println("|--------------------------------|-----------|---------------|------------------|");
    }
    private static void StadiumsTitle()
    {
        System.out.println("|---------------|-------|------|");
        System.out.println("|     Назва     | Місця | Ціна |");
        System.out.println("|---------------|-------|------|");
    }
    private static void PlayersTitle()
    {
        System.out.println("|---------------|---------------|-----------|------|-----------|-------|---------------|");
        System.out.println("|     Ім'я      |    Прізвище   |   Дата    |Статус|  Здоров'я |Зарпл. |     Команда   |");
        System.out.println("|---------------|---------------|-----------|------|-----------|-------|---------------|");
    }
}
