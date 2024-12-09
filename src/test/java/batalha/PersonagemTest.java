
package batalha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatalhaTests {

    // Testes para atributos somando 20
    @Test
    void testSomatorioAtributos19() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Assassino(7, 4, 4, 4));
        assertEquals("Somatório dos atributos deve ser igual a 20.", exception.getMessage());
    }

    @Test
    void testSomatorioAtributos20() {
        assertDoesNotThrow(() -> new Guerreiro(6, 5, 5, 4));
    }

    @Test
    void testSomatorioAtributos21() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Guerreiro(7, 6, 5, 3));
        assertEquals("Somatório dos atributos deve ser igual a 20.", exception.getMessage());
    }

    // Testes para valores mínimos
    @Test
    void testValorMinimoAtributo2() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Assassino(2, 6, 6, 6));
        assertEquals("Todos os atributos devem ter no mínimo 3 pontos.", exception.getMessage());
    }

    @Test
    void testValorMinimoAtributo3() {
        assertDoesNotThrow(() -> new Guerreiro(3, 7, 5, 5));
    }

    // Testes para regras de classe
    @Test
    void testRegraClasseAssassinoInvalido() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Assassino(6, 5, 7, 3));
        assertEquals("Ataque e Velocidade devem ser os mais altos ou empatados.", exception.getMessage());
    }

    @Test
    void testRegraClasseGuerreiroInvalido() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Guerreiro(7, 3, 4, 6));
        assertEquals("Defesa e Velocidade não podem ultrapassar Resistência ou Ataque.", exception.getMessage());
    }

    @Test
    void testRegraClasseValido() {
        assertDoesNotThrow(() -> new Guerreiro(7, 3, 3, 7));
        assertDoesNotThrow(() -> new Assassino(7, 3, 7, 3));
    }

    // Testes de funcionalidade de batalha
    @Test
    void testEvasaoAtaque() {
        Personagem p1 = new Assassino(7, 3, 7, 3);
        Personagem p2 = new Guerreiro(7, 3, 3, 7);
        Batalha batalha = new Batalha(p1, p2);
        batalha.realizarAtaque();
        assertTrue(p2.getVida() < 35 || p1.getVida() < 15);
    }

    @Test
    void testDanoCritico() {
        Personagem p1 = new Assassino(7, 3, 7, 3);
        Personagem p2 = new Guerreiro(7, 3, 3, 7);
        Batalha batalha = new Batalha(p1, p2);
        batalha.realizarAtaque();
        assertNotEquals(p2.getVida(), 35); // Assume que o dano foi causado
    }

    // Testes de MC/DC para decisão do primeiro atacante
    @Test
    void testPrimeiroAtacanteP1MaisRapido() {
        Personagem p1 = new Assassino(7, 3, 7, 3);
        Personagem p2 = new Guerreiro(7, 3, 3, 7);
        Batalha batalha = new Batalha(p1, p2);
        assertEquals(p1, batalha.getVencedor());
    }

    @Test
    void testPrimeiroAtacanteP2MaisRapido() {
        Personagem p1 = new Assassino(7, 3, 3, 7);
        Personagem p2 = new Guerreiro(7, 3, 7, 3);
        Batalha batalha = new Batalha(p1, p2);
        assertEquals(p2, batalha.getVencedor());
    }

    @Test
    void testPrimeiroAtacanteVelocidadesIguaisRandom0() {
        Personagem p1 = new Assassino(7, 3, 7, 3);
        Personagem p2 = new Guerreiro(7, 3, 7, 3);
        Batalha batalha = new Batalha(p1, p2); // Depende do random interno
        assertNotNull(batalha.getVencedor());
    }

    @Test
    void testPrimeiroAtacanteVelocidadesIguaisRandom1() {
        Personagem p1 = new Assassino(7, 3, 7, 3);
        Personagem p2 = new Guerreiro(7, 3, 7, 3);
        Batalha batalha = new Batalha(p1, p2); // Depende do random interno
        assertNotNull(batalha.getVencedor());
    }
}
