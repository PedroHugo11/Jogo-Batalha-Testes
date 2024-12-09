package batalha;

import static java.lang.Math.min;
import java.security.SecureRandom;

public class Batalha {
	private final Personagem primeiroAtacante;
	private final Personagem segundoAtacante;
	private final SecureRandom geradorRandomico;

	public Batalha(Personagem p1, Personagem p2) {
		this.geradorRandomico = new SecureRandom();
		if (p1.getVelocidade() > p2.getVelocidade()) {
			this.primeiroAtacante = p1;
			this.segundoAtacante = p2;
		} else if (p1.getVelocidade() < p2.getVelocidade()) {
			this.primeiroAtacante = p2;
			this.segundoAtacante = p1;
		} else {
			if (geradorRandomico.nextInt(2) == 0) {
				this.primeiroAtacante = p1;
				this.segundoAtacante = p2;
			} else {
				this.primeiroAtacante = p2;
				this.segundoAtacante = p1;
			}
		}
	}

	public void realizarAtaque() {
		realizarAtaque(primeiroAtacante, segundoAtacante);
		if (segundoAtacante.getVida() > 0) {
			realizarAtaque(segundoAtacante, primeiroAtacante);
		}
	}

	private void realizarAtaque(Personagem atacante, Personagem defensor) {
		int chanceEvasao = calcularChanceEvasao(atacante, defensor);
		if (!evadiu(chanceEvasao)) {
			double modificadorAtaque = 0.8 + geradorRandomico.nextDouble() * 0.4;
			boolean eGolpeCritico = geradorRandomico.nextInt(100) < 10;
			atacante.atacar(defensor, modificadorAtaque, eGolpeCritico);
		}
	}

	private int calcularChanceEvasao(Personagem atacante, Personagem defensor) {
		return min(50, (defensor.getVelocidade() - atacante.getVelocidade()) * 2);
	}

	private boolean evadiu(int chanceEvasao) {
		return geradorRandomico.nextInt(100) < chanceEvasao;
	}

	public boolean temVencedor() {
		return primeiroAtacante.getVida() <= 0 || segundoAtacante.getVida() <= 0;
	}

	public Personagem getVencedor() {
		if (primeiroAtacante.getVida() > 0) return primeiroAtacante;
		return segundoAtacante.getVida() > 0 ? segundoAtacante : null;
	}
}