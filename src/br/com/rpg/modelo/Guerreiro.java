package br.com.rpg.modelo;

/**
 * Guerreiro — combatente corpo a corpo com alta vida e defesa.
 * Especialidade: ataques físicos poderosos e investidas devastadoras.
 */
public class Guerreiro extends Personagem {

    // ─── Construtor ────────────────────────────────────────────────────────────

    /**
     * Cria um Guerreiro com atributos padrão da classe.
     *
     * @param nome Nome do guerreiro
     */
    public Guerreiro(String nome) {
        super(nome, 150, 30, 25, 20);
    }

    // ─── Métodos abstratos implementados ──────────────────────────────────────

    /**
     * Ataque padrão com dano aumentado (×1.2).
     */
    @Override
    public void atacar(Personagem alvo) {
        System.out.printf("  ⚔️  %s desfere um golpe poderoso em %s!%n", nome, alvo.getNome());
        int dano = (int) (ataque * 1.2);
        boolean morreu = alvo.receberDano(dano);
        if (morreu) {
            ganharExperiencia(50);
        }
    }

    /**
     * Habilidade especial: Investida Furiosa — custo 20 de mana, dano ×2.5.
     */
    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        int custo = 20;
        if (mana < custo) {
            System.out.printf("  ❌ %s não tem mana suficiente para Investida Furiosa! (%d/%d)%n",
                    nome, mana, custo);
            return;
        }

        System.out.println();
        System.out.printf("  🔥 %s reúne todas as suas forças...%n", nome);
        System.out.printf("  💥 INVESTIDA FURIOSA! %s se lança contra %s com fúria devastadora!%n",
                nome, alvo.getNome());

        int dano = (int) (ataque * 2.5);
        mana -= custo;
        boolean morreu = alvo.receberDano(dano);
        System.out.printf("  🔵 Mana restante de %s: %d/%d%n", nome, mana, manaMaxima);

        if (morreu) {
            ganharExperiencia(50);
        }
        System.out.println();
    }

    @Override
    public String getTipo() {
        return "Guerreiro";
    }
}
