public class OrientOper {

    public OrientOper(){

    }

    public Coord makeStep(Orientation orient, Coord coord)
    {
        if (orient == Orientation.STRAIGHT)
            return new Coord(coord.x, coord.y + 1);
        if (orient == Orientation.RIGHT)
            return new Coord(coord.x - 1, coord.y);
        if (orient == Orientation.BACK)
            return new Coord(coord.x, coord.y - 1);
        if (orient == Orientation.LEFT)
            return new Coord(coord.x + 1, coord.y);

        return null;
    }

    public Orientation getOrient(int or)
    {
        if (or == 0)
            return Orientation.STRAIGHT;
        if (or == 1)
            return Orientation.RIGHT;
        if (or == 2)
            return Orientation.BACK;
        if (or == 3)
            return Orientation.LEFT;

        return null;
    }

    public Orientation goBack(Orientation orient)
    {
        return getOrient((orient.or + Orientation.BACK.or) % 4);//поворот на 180 градусов по часовой
    }


    public Orientation changeOrient(Orientation orient, char way)
    {
        //используется такой метод определения направления (север, юг и т.д.) чтобы не было привязки к конкретным значениям
        if (way == 'R')
            return getOrient((orient.or + Orientation.RIGHT.or) % 4);//поворот на 90 градусов по часовой

        return getOrient((orient.or + Orientation.LEFT.or) % 4);//поворот на 270 градусов по часовой
    }
}
