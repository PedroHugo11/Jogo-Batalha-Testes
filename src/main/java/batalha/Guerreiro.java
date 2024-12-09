package batalha;

public class Guerreiro extends Personagem {

	public Guerreiro(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	@Override
	final void checarRegraDeClasse() {
		if (!(getResistencia() >= getAtaque() && getAtaque() >= getResistencia())) {
			throw new IllegalStateException("Resistência e Ataque devem ser os mais altos ou empatados.");
		}
		if (getDefesa() > getResistencia() || getVelocidade() > getAtaque()) {
			throw new IllegalStateException("Defesa e Velocidade não podem ultrapassar Resistência ou Ataque.");
		}
	}
}
