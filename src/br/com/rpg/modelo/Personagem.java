package br.com.rpg.modelo;

/**
 * Classe abstrata base para todos os personagens do RPG "Legends of Java".
 * Define atributos comuns e comportamentos compartilhados entre todos os tipos
 * de personagem, além de declarar métodos abstratos que cada subclasse deve implementar.
 */
public abstract class Personagem {

    // ─── Atributos protegidos ──────────────────────────────────────────────────
    protected String nome;
    protected int    nivel;
    protected int    vida;
    protected int    vidaMaxima;
    protected int    mana;
    protected int    manaMaxima;
    protected int    ataque;
    protected int    defesa;
    protected int    experiencia;

    // ─── Construtor ────────────────────────────────────────────────────────────

    /**
     * @param nome    Nome do personagem
     * @param vida    Vida inicial (e máxima)
     * @param mana    Mana inicial (e máxima)
     * @param ataque  Poder de ataque base
     * @param defesa  Poder de defesa base
     */
    public Personagem(String nome, int vida, int mana, int ataque, int defesa) {
        this.nome        = nome;
        this.nivel       = 1;
        this.experiencia = 0;
        this.vidaMaxima  = vida;
        this.vida        = vida;
        this.manaMaxima  = mana;
        this.mana        = mana;
        this.ataque      = ataque;
        this.defesa      = defesa;
    }

    // ─── Métodos concretos ─────────────────────────────────────────────────────

    /**
     * Exibe o status completo do personagem formatado em uma caixa.
     */
    public void exibirStatus() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.printf("║  %-36s║%n", getTipo() + " — " + nome);
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf("║  Nível    : %-25d║%n", nivel);
        System.out.printf("║  Vida     : %d / %-21d║%n", vida, vidaMaxima);
        System.out.printf("║  Mana     : %d / %-21d║%n", mana, manaMaxima);
        System.out.printf("║  Ataque   : %-25d║%n", ataque);
        System.out.printf("║  Defesa   : %-25d║%n", defesa);
        System.out.printf("║  XP       : %-25d║%n", experiencia);
        System.out.println("╚══════════════════════════════════════╝");
    }

    /**
     * Calcula e aplica o dano recebido, descontando a defesa.
     *
     * @param dano Dano bruto recebido
     * @return true se o personagem morreu após o golpe
     */
    public boolean receberDano(int dano) {
        int danoReal = Math.max(1, dano - (defesa / 2)); // mínimo 1 de dano
        vida = Math.max(0, vida - danoReal);
        System.out.printf("  💥 %s recebe %d de dano (-%d após defesa). Vida: %d/%d%n",
                nome, dano, danoReal, vida, vidaMaxima);
        if (!estaVivo()) {
            System.out.printf("  ☠️  %s foi derrotado!%n", nome);
            return true;
        }
        return false;
    }

    /**
     * Cura o personagem por uma quantidade de vida, sem exceder o máximo.
     *
     * @param quantidade Quantidade de vida a restaurar
     */
    public void curar(int quantidade) {
        int antes = vida;
        vida = Math.min(vida + quantidade, vidaMaxima);
        System.out.printf("  💚 %s foi curado em %d pontos de vida. Vida: %d/%d%n",
                nome, (vida - antes), vida, vidaMaxima);
    }

    /**
     * Adiciona experiência e verifica se o personagem sobe de nível.
     *
     * @param exp Pontos de experiência ganhos
     */
    public void ganharExperiencia(int exp) {
        experiencia += exp;
        System.out.printf("  ✨ %s ganhou %d de experiência! (Total: %d/100)%n", nome, exp, experiencia);
        if (experiencia >= 100) {
            subirNivel();
        }
    }

    /**
     * Sobe o nível do personagem, aumentando todos os atributos.
     */
    public void subirNivel() {
        nivel++;
        vidaMaxima  += 20;
        manaMaxima  += 10;
        ataque      += 5;
        defesa      += 3;
        vida        = vidaMaxima;   // restaura ao máximo
        mana        = manaMaxima;
        experiencia = 0;

        System.out.println();
        System.out.println("  ╔══════════════════════════════╗");
        System.out.printf("  ║  🌟 LEVEL UP! — %s%n", nome);
        System.out.printf("  ║  Nível %d → %d%n", nivel - 1, nivel);
        System.out.println("  ║  Vida, Mana, Ataque e Defesa aumentados!");
        System.out.println("  ╚══════════════════════════════╝");
        System.out.println();
    }

    /**
     * @return true se o personagem ainda está vivo
     */
    public boolean estaVivo() {
        return vida > 0;
    }

    // ─── Getters ───────────────────────────────────────────────────────────────

    public String getNome()        { return nome; }
    public int    getNivel()       { return nivel; }
    public int    getVida()        { return vida; }
    public int    getVidaMaxima()  { return vidaMaxima; }
    public int    getMana()        { return mana; }
    public int    getManaMaxima()  { return manaMaxima; }
    public int    getAtaque()      { return ataque; }
    public int    getDefesa()      { return defesa; }
    public int    getExperiencia() { return experiencia; }

    // ─── Setters controlados ───────────────────────────────────────────────────

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(mana, manaMaxima));
    }

    // ─── Métodos abstratos ─────────────────────────────────────────────────────

    /**
     * Realiza o ataque padrão do personagem contra um alvo.
     * Cada subclasse define seu próprio estilo de ataque.
     *
     * @param alvo Personagem que receberá o ataque
     */
    public abstract void atacar(Personagem alvo);

    /**
     * Usa a habilidade especial única do personagem.
     *
     * @param alvo Alvo da habilidade
     */
    public abstract void usarHabilidadeEspecial(Personagem alvo);

    /**
     * @return O tipo/classe do personagem como String
     */
    public abstract String getTipo();
}
