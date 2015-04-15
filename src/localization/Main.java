package localization;

/**
 * Created by alex on 4/15/15.
 */
public class Main
{
    /** Simple test file */

    public static void main(String[] args)
    {
        System.out.println(Loc.get("menu_fileOpen"));
        System.out.println(Loc.get("menu_fileClose"));

        Loc.setActiveLang("norwegian");

        System.out.println(Loc.get("menu_fileOpen"));
        System.out.println(Loc.get("menu_fileClose"));
    }
}
