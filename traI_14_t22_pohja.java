// traI_14_t22_pohja.java SJ

import java.util.*;

public class traI_14_t22_pohja {
    // P‰‰ohjelman k‰yttˆ:
    // java traI_14_t22_pohja [merkkijono]
    public static void main(String[] args) {
        String mjono = null;
        if (args.length > 0)
            mjono = args[0];
        if (mjono == null) {
            System.out.print("Anna merkkijono : ");
            Scanner sc = new Scanner(System.in);
            mjono = sc.nextLine();
        }
        System.out.print("Merkkijono '" + mjono + "' ");
        if (onkoPalindromi(mjono))
            System.out.println("on palindromi");
        else
            System.out.println("ei ole palindromi");
    } // main()
    /**
      * Apumetodi joka muuttaa merkkijonon merkkipakaksi.
      */
    public static Deque<Character> merkkijonostaPakka(String S) {
        Deque<Character> D = new LinkedList<Character>();
        for (int i = 0; i < S.length(); i++)
            D.addLast(S.charAt(i));
        return D;
    }
    /**
     * 22) Palindromi on merkkijono joka myˆs takaperin luettuna on sama. Kun
     * sana talletetaan pakkaan merkki kerrallaan, on helppoa tarkastaa onko
     * sana palindromi vai ei. Kirjoita algoritmi joka tallettaa merkkijonon
     * merkit pakkaan ja joka tarkastaa onko pakan sis‰ltˆ palindromi vai ei.
     * Ota kurssin www-sivulta p‰‰ohjelma jossa on vinkkej‰ miten merkkijono
     * muutetaan pakaksi. Aikavaativuus?
     */
    public static boolean onkoPalindromi(String S) {
    	//Stringin pituus tallennetaan javassa fieldiin ---> O(1) length-kutsulle
    	Integer merkkiJononKeskelle = S.length();
    	//Katsotaan onko luku parillinen, jos ei ole lis‰t‰‰n yhdell‰
    	if (merkkiJononKeskelle % 2 != 0) {
			merkkiJononKeskelle++;
		}
    	Integer inMerkkiJononKeskella = 0;
        Deque<Character> D = merkkijonostaPakka(S);
        Iterator<Character> forward = D.iterator();
        Iterator<Character> rewind = D.descendingIterator();
        //T(n) ----> n/2 ----> O(n)
        while (forward.hasNext()&& inMerkkiJononKeskella < merkkiJononKeskelle) {
        	inMerkkiJononKeskella++;
			Character palindromiTsekki = forward.next();
			Character palindroCharacter = rewind.next();
			if (palindroCharacter.equals(palindromiTsekki)) {
				continue;
			}
			else {
				return false;
			}
		}
        return true;
    }
} // class

