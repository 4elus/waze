import java.io.IOException;

public class MakeRound {
    FileConstructor f;
    String[] strings;
    OrientOper op;

    public MakeRound(FileConstructor f) throws IOException {
        this.f = f;
        this.strings = f.getStrings();
        op = new OrientOper();
    }

    public void makeLoop() throws IOException {
        for(int i = 0; i < strings.length; i++)
        {
            String[] str = strings[i].split(" ");
            //прямой обход (от входа к выходу)
            int[] resultStraight = makeOneRound(str[0], 0, 0, Orientation.STRAIGHT, 0,0,0);
            Coord coord = new Coord(resultStraight[0], resultStraight[1]);

            Orientation orient = op.goBack(op.getOrient(resultStraight[2]));
            int maxY = resultStraight[3];
            int maxX = resultStraight[4];
            int minX = resultStraight[5];
            //сохранены для работы с матрицей - координаты точки выхода
            int xX = coord.x;
            int yY = coord.y;
            Orientation orientForMap = op.getOrient(orient.or);
            //////////////////////////////////////////////////////////
            //обратный обход (от выхода к входу)
            int[] resultBack = makeOneRound(str[1], coord.x, coord.y, orient, maxY, maxX, minX);
            coord = new Coord(resultBack[0], resultBack[1]);
            orient = op.goBack(op.getOrient(resultBack[2]));
            maxY = resultBack[3];
            maxX = resultBack[4];
            minX = resultBack[5];


            //цикл походов завершен, дальше следующий этап - построение матрицы для лабиринта с вариантами путей для каждой клетки
            //определение размеров матрица (ширина и высота клеток лабиринта)

            int lenY = maxY + 1; //т.к. У := [0; maxY]
            int lenX = maxX - minX + 1; //правило нахождени кол-ва жлементов между двумя числами
            int[][] map = new int[lenX][lenY];
            //1 - заполним ходы нулями
            for (int[] el: map) {
                for (int j = 0; j < el.length; j++)
                    el[j] = 0;
            }
            //1 (прямо), 2 (обратно), 4 (налево), 8(направо)- это ходы только с одним напрвлением
            int xIn = 0 - minX; //координата Х точки входа в лабиринт в новой системе (в map)
            xX -= minX; //поулчаем координату точки выхода в новой системе(в map)

            map[xIn][0] |= 1; //первый ход всегда прямо
            switch (orientForMap){
                case STRAIGHT: map[xX][yY] |= 1;
                    break;
                case BACK: map[xX][yY] |= 2;
                    break;
                case LEFT: map[xX][yY] |= 4;
                    break;
                case RIGHT: map[xX][yY] |= 8;
                    break;
            }

            //straight
            coord = new Coord(xIn, 0);
            orient = Orientation.STRAIGHT;
            map = fillOneRound(str[0], coord, orient, map);
            //back
            coord = new Coord(xX, yY);
            orient = orientForMap;
            map = fillOneRound(str[1], coord, orient, map);

            //make output doc
            f.writer.write("Case #" + (i+1) + ": \n");
            for(int row = 0; row < lenY; row++){
                for (int col = 0; col < lenX; col++) {
                    if (map[col][row] < 10)
                        f.writer.write(""+ map[col][row]);
                    else
                        f.writer.write('a' + (map[col][row] - 10));
                }
                f.writer.write("\n");
            }
        }
        f.close();
    }

    public int[] makeOneRound(String string, int x, int y, Orientation orient, int maxY, int maxX, int minX)
    {
        Coord coord = new Coord(x, y);
        for(int i = 1; i < string.length()-1; i++)
        {
            if (string.charAt(i) == 'W')
            {
                coord = op.makeStep(orient, coord);
                if (coord.x < minX)
                    minX = coord.x;
                if (coord.x > maxX)
                    maxX = coord.x;
                if (coord.y > maxY)
                    maxY = coord.y;
            }
            else{
                orient = op.changeOrient(orient, string.charAt(i));
            }

        }
        return new int[]{coord.x, coord.y, orient.or, maxY, maxX, minX};
    }

    public int[][] fillOneRound(String string, Coord coord, Orientation orient, int[][] map)
    {
        Coord cdProm;
        int[] stepCase = null;
        for(int i = 1; i < string.length()-1; i++)
        {
            if (string.charAt(i) == 'W')
            {
                cdProm = op.makeStep(orient, coord);
                switch (orient){
                    case STRAIGHT: stepCase = new int[]{2, 1};
                        break;
                    case BACK: stepCase = new int[]{1, 2};
                        break;
                    case LEFT: stepCase = new int[]{8, 4};
                        break;
                    case RIGHT: stepCase = new int[]{4, 8};
                        break;
                }
                map[coord.x][coord.y] |= stepCase[0];
                map[cdProm.x][cdProm.y] |= stepCase[1];
                coord = cdProm;
            }
            else{
                orient = op.changeOrient(orient, string.charAt(i));
            }
        }

        return map;
    }
}
