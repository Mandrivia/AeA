package Tests;

/**
 * Classe permettant de v�rifier le simulateur avec des exemples version
 * brackets
 * 
 * @author Delphine
 *
 */
public class CheckSimulateur {
	char[] preMiARN;

	public CheckSimulateur(char[] preMiARN) {
		this.preMiARN = preMiARN;
	}

	/**
	 * V�rifie que la taille de la tige boucle est correcte
	 * 
	 * @return
	 */
	public boolean checkSize() {
		return ((70 <= this.preMiARN.length) && (this.preMiARN.length <= 100));
	}

	/**
	 * V�rifie qu'il y a au moins 48 nucl�otides dans des apppariements
	 * 
	 * @return
	 */
	public boolean enoughMatching() {
		int nb = 0;
		for (int i = 0; i < this.preMiARN.length; i++) {
			if ((preMiARN[i] == '(') || (preMiARN[i] == ')')) {
				nb++;
			}
		}
		return (nb >= 48);
	}

	/**
	 * V�rifie que les appariements se font bien dans le bon ordre
	 * 
	 * @return
	 */
	public boolean goodMatching() {
		boolean before = true;
		for (int i = 0; i < this.preMiARN.length; i++) {
			if (this.preMiARN[i] == ')') {
				before = false;
			}
			if ((before == false) && (this.preMiARN[i] == '(')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * V�rifie que le nombre d'appariements � la suite est toujours au moins de
	 * 3
	 * 
	 * @return
	 */
	public boolean atLeastThreeMatching() {
		int i = 0;
		while (i < this.preMiARN.length) {
			if (preMiARN[i] == '(') {
				if ((preMiARN[i + 1] != '(') || (preMiARN[i + 2] != '(')) {
					return false;
				}
				while ((i<this.preMiARN.length)&&(preMiARN[i] == '(')) {
					i++;
				}
			} else {
				if (preMiARN[i] == ')') {
					if ((preMiARN[i + 1] != ')') || (preMiARN[i + 2] != ')')) {
						return false;
					}
					while ((i<this.preMiARN.length)&&(preMiARN[i] == ')')) {
						i++;
					}
				} else {
					i++;
				}
			}
		}
		return true;
	}

	/**
	 * V�rifie que les boucles ne d�passent pas le quotas autoris�
	 * 
	 * @return
	 */
	public boolean nbInLoop() {
		int i = this.preMiARN.length / 2;
		int j = i - 1;
		int nb = 0;
		/* V�rification de la boucle finale */
		while (this.preMiARN[j] == '.') {
			nb++;
			j--;
		}
		while (this.preMiARN[i] == '.') {
			nb++;
			i++;
		}
		if (nb > 8) {
			System.out.println("boucle finale");
			return false;
		}
		/* V�rification � gauche (pas plus de 3) */
		while (j >= 0) {
			if (preMiARN[j] == '.') {
				if ((j - 3 >= 0) && (preMiARN[j - 3] == '.')) {
					System.out.println("boucles gauche");
					return false;
				}
				j -= 3;
			}
			while ((j >= 0) && (preMiARN[j] == '(')) {
				j--;
			}
			j--;
		}

		while (i < preMiARN.length) {
			if (preMiARN[i] == '.') {
				if ((i + 3 < preMiARN.length) && (preMiARN[i + 3] == '.')) {
					System.out.println("boucles droite "+i);
					return false;
				}
				j += 3;
			}
			while ((i < preMiARN.length) && (preMiARN[i] == ')')) {
				i++;
			}
			i++;
		}
		return true;
	}

}
