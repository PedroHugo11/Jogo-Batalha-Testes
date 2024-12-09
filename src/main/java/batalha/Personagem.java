package batalha;

public abstract class Personagem {
	private Integer ataque;
	private Integer defesa;
	private Integer velocidade;
	private Integer resistencia;
	private Integer vida;

	public Personagem(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		this.ataque = ataque;
		this.defesa = defesa;
		this.velocidade = velocidade;
		this.resistencia = resistencia;

		checarTotal();
		checarValorMinimo();
		checarRegraDeClasse();

		this.vida = 5 * this.resistencia;
	}

	abstract void checarRegraDeClasse();

	private void checarValorMinimo() {
		if (ataque < 3 || defesa < 3 || velocidade < 3 || resistencia < 3) {
			throw new IllegalStateException("Todos os atributos devem ter no mínimo 3 pontos.");
		}
	}

	final void checarTotal() {
		if (this.ataque + this.defesa + this.velocidade + this.resistencia != 20) {
			throw new IllegalStateException("Somatório dos atributos deve ser igual a 20.");
		}
	}

	public void atacar(Personagem defensor, double modificadorAtaque, boolean eGolpeCritico) {
		int danoBase = calcularDanoBase(modificadorAtaque);
		int dano = calcularDanoInfringindo(danoBase, defensor.getDefesa(), eGolpeCritico);
		defensor.receberDano(dano);
	}

	private void receberDano(int danoInfringido) {
		this.vida = Math.max(this.vida - danoInfringido, 0);
	}

	public int calcularDanoBase(double modificadorAtaque) {
		return (int) Math.round(this.ataque * modificadorAtaque);
	}

	int calcularDanoInfringindo(int danoBase, int defesa, boolean eGolpeCritico) {
		int dano = Math.max(danoBase + this.ataque - defesa, 1);
		return eGolpeCritico ? (int) (dano * 1.5) : dano;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	public Integer getResistencia() {
		return resistencia;
	}

	public Integer getVida() {
		return vida;
	}
}
