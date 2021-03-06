package Tests;

public class Simulateur {

	public Simulateur() {

	}

	/* G�n�re un pr� microARN sans boucle A METTRE DANS UN FICHIER GENOME */
	public char[] genPreMiARNSansBoucle(boolean brackets) {
		char[] alphabet = { 'A', 'C', 'U', 'G' };
		char[] reverseAlphabet = { 'U', 'G', 'A', 'C' };
		int taille = (int) (Math.random() * (101 - 70)) + 70;
		System.out.println(taille);
		char[] preMiARN = new char[taille];
		int rdm;
		if (brackets) {
			for (int i = 0; i < taille / 2 + 1; i++) {
				preMiARN[i] = '(';
				preMiARN[taille - i - 1] = ')';
			}
		} else {
			for (int i = 0; i < taille / 2 + 1; i++) {
				rdm = (int) (Math.random() * 3);
				preMiARN[i] = alphabet[rdm];
				preMiARN[taille - i - 1] = reverseAlphabet[rdm];
			}
		}
		for (int j = 0; j < taille; j++) {
			System.out.print(preMiARN[j] + " ");
		}
		return preMiARN;
	}

	/* G�n�re un pr� microARN avec des boucles */
	public char[] genPreMiARNBoucles(boolean brackets) {
		char[] alphabet = { 'A', 'C', 'U', 'G' };
		char[] reverseAlphabet = { 'U', 'G', 'A', 'C' };
		/* Taille du preMiARN */
		int taille = (int) (Math.random() * (101 - 70)) + 70;
		System.out.println("Taille : " + taille);
		char[] preMiARN = new char[taille];
		for (int i = 0; i < preMiARN.length; i++) {
			preMiARN[i] = '-';
		}

		/* Calcul du nombre de nucléotides dans les boucles */
		int nucleotidesSansBoucle = (int) (Math.random() * (taille + 1 - 51)) + 48;
		if (nucleotidesSansBoucle % 2 == 1) {
			nucleotidesSansBoucle--;
		}
		System.out.println("nucleotides appari�es : " + nucleotidesSansBoucle);
		int nucleotidesDansBoucle = taille - nucleotidesSansBoucle;
		System.out
				.println("nucleotides dans boucle : " + nucleotidesDansBoucle);

		/* Calcul du nombre de nucléotides dans la boucle finale */
		int max = 8;
		int min = 0;
		if (nucleotidesDansBoucle < 8)
			max = nucleotidesDansBoucle;
		int nucleotidesBoucleFinale = (int) (Math.random() * (max + 1 - min))
				+ min;
		System.out.println("boucle finale : " + nucleotidesBoucleFinale);
		nucleotidesDansBoucle -= nucleotidesBoucleFinale;

		/* Création du preMiARN */
		/* Insertion de la boucle finale */
		int i = taille / 2 - nucleotidesBoucleFinale / 2;
		int j = i + nucleotidesBoucleFinale;
		for (int k = i; k < j; k++) {
			if (!brackets) {
				int rdm = (int) (Math.random() * 4);
				preMiARN[k] = alphabet[rdm];
			} else {
				preMiARN[k] = '.';
			}
		}
		i--;
		j++;

		/* Insertion du reste des nucléotides */
		/* Boucles à gauche de la boucle finale */
		int itmp = i;
		while (itmp >= 13 && nucleotidesDansBoucle > 0) {
			int placeBoucle = (int) (Math.random() * (11 - 3) + 3);
			int nbNucleotides = (int) (Math.random() * (4 - 1) + 1);
			int rdm = (int) (Math.random() * 4);
			itmp -= placeBoucle;
			nucleotidesDansBoucle -= nbNucleotides;
			for (int m = 1; m <= nbNucleotides; m++) {
				if (brackets) {
					preMiARN[itmp] = '.';
				} else {
					preMiARN[itmp] = alphabet[rdm];
				}
				itmp--;
			}
		}

		/* Boucles à droite de la boucle finale */
		int jtmp = j;
		while (jtmp <= taille - 13 && nucleotidesDansBoucle > 0) {
			int placeBoucle = (int) (Math.random() * (11 - 3) + 3);
			int nbNucleotides = (int) (Math.random() * (4 - 1) + 1);
			int rdm = (int) (Math.random() * 4);
			jtmp += placeBoucle;
			nucleotidesDansBoucle -= nbNucleotides;
			for (int m = 1; m <= nbNucleotides; m++) {
				if (brackets) {
					preMiARN[jtmp] = '.';
				} else {
					preMiARN[jtmp] = alphabet[rdm];
				}
				jtmp++;
			}
		}

		/* Reste des appariements */
		j--;
		while (i >= 0 && j < taille) {
			while ((i >= 0) && (preMiARN[i] != '-')) {
				i--;
			}
			while ((j < taille) && (preMiARN[j] != '-')) {
				j++;
			}
			while (((i >= 0) && (j < taille)) && (preMiARN[i] == '-')
					&& (preMiARN[j] == '-')) {
				if (brackets) {
					preMiARN[i] = '(';
					preMiARN[j] = ')';
				} else {
					int rdm = (int) (Math.random() * 4);
					preMiARN[i] = alphabet[rdm];
					preMiARN[j] = reverseAlphabet[rdm];
				}
				i--;
				j++;
			}
		}

		/* Remplissage des derniers restants */
		if (i > -1) {
			while (i >= 0) {
				if (brackets) {
					preMiARN[i] = '(';
				} else {
					int rdm = (int) (Math.random() * 4);
					preMiARN[i] = alphabet[rdm];
				}
				i--;
			}
		}

		if (j < taille) {
			while (j < taille) {
				if (brackets) {
					preMiARN[j] = ')';
				} else {
					int rdm = (int) (Math.random() * 4);
					preMiARN[j] = alphabet[rdm];
				}
				j++;
			}
		}

		for (int l = 0; l < preMiARN.length; l++) {
			System.out.print(preMiARN[l] + " ");
		}
		System.out.println();
		/*
		 * for (int l = 0; l < preMiARN.length; l++) { System.out.println(l +
		 * " : " + " " + preMiARN[l]); }
		 */

		return preMiARN;
	}

}
