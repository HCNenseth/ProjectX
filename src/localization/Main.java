package localization;

/**
 * Created by alex on 4/15/15.
 */
public class Main
{
    /* Simple test file - will be deleted soon */

    public static void main(String[] args)
    {
        System.out.printf("=> Active lang: %s\n", Loc.getActiveLang());
        System.out.println(Loc.get("menu_fileOpen"));
        System.out.println(Loc.get("menu_fileClose"));

        Loc.setActiveLang("norwegian");

        System.out.printf("=> Active lang: %s\n", Loc.getActiveLang());
        System.out.println(Loc.get("menu_fileOpen"));
        System.out.println(Loc.get("menu_fileClose"));
    }
}