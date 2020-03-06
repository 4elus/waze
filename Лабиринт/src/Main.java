import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File read = new File("in.txt");  // считываем файл
        File write = new File("out.txt");

        BufferedReader reader = new BufferedReader(new FileReader(read));
        BufferedWriter writer = new BufferedWriter(new FileWriter(write));

        String note;
        note  = reader.readLine(); // считываем файл построчно
        int n = Integer.parseInt(note); // переводим в число т.к. первое строка в файле - число

        for (int i = 1; i <= n; i++) {
            note  = reader.readLine();
            String[] arr = note.split(" "); // путь выход/вход из лабиринта
            int x = 0, y = 0, dir = 0;
            int min_x = 0, max_y = 0, max_x = 0;

            for (int j = 1; j < arr[0].length()-1; j++) {
                // определяем направление, где право - x++; лево - x-- ...
                if (arr[0].charAt(j) == 'W'){
                    switch (dir){
                        case 0:
                            y++;
                            break;
                        case 1:
                            x--;
                            break;
                        case 2:
                            y--;
                            break;
                        default:
                            x++;
                            break;
                    }

                    // ?
                    if(x < min_x)
                        min_x = x;
                    if(x > max_x)
                        max_x = x;
                    if(y > max_y)
                        max_y = y;

                }else if(arr[0].charAt(i) == 'R'){
                    dir = (dir + 1) % 4;
                }else if(arr[0].charAt(i) == 'L'){
                    dir = (dir + 3) % 4;
                }
            }
            // разворот (обратный путь)
            int turn = (dir + 2) % 4;

            dir = turn;
            for (int j = 1; j < arr[1].length()-1; j++) {
                // определяем направление, где право - x++; лево - x-- ...
                if (arr[1].charAt(j) == 'W'){
                    switch (dir){
                        case 0:
                            y++;
                            break;
                        case 1:
                            x--;
                            break;
                        case 2:
                            y--;
                            break;
                        default:
                            x++;
                            break;
                    }


                    // ?
                    if(x < min_x)
                        min_x = x;
                    if(x > max_x)
                        max_x = x;
                    if(y > max_y)
                        max_y = y;

                }else if(arr[0].charAt(i) == 'R'){
                    dir = (dir + 1) % 4;
                }else if(arr[0].charAt(i) == 'L'){
                    dir = (dir + 3) % 4;
                }
            }
        }
    }
}
