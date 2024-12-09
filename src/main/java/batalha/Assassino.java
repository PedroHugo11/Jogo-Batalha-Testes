package batalha;

public class Assassino extends Personagem {

	public Assassino(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	@Override
	final void checarRegraDeClasse() {
		if (!(getAtaque() >= getVelocidade() && getVelocidade() >= getAtaque())) {
			throw new IllegalStateException("Ataque e Velocidade devem ser os mais altos ou empatados.");
		}
		if (getResistencia() > getAtaque() || getDefesa() > getVelocidade()) {
			throw new IllegalStateException("Resistência e Defesa não podem ultrapassar Ataque e Velocidade.");
		}
	}
}
