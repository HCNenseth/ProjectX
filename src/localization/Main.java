package localization;

/**
 * Created by alex on 4/15/15.
 */
public class Main
{
    /** Simple test file */

    public static void main(String[] args)
    {
        Loc l = new Loc();

        System.out.println(l.get("menu_fileOpen"));
        System.out.println(l.get("menu_fileClose"));

        l.setActiveLang("norwegian");

        System.out.println(l.get("menu_fileOpen"));
        System.out.println(l.get("menu_fileClose"));
    }
}
