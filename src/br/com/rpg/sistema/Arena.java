package br.com.rpg.sistema;

import br.com.rpg.modelo.Personagem;
import java.util.ArrayList;

/**
 * Arena — sistema de batalha do RPG "Legends of Java".
 * Gerencia duelos individuais e torneios eliminatórios.
 */
public class Arena {

    /**
     * Simula uma batalha completa entre dois personagens até um deles ser derrotado.
     *
     * @param p1 Primeiro personagem (ataca primeiro)
     * @param p2 Segundo personagem
     * @return O personagem vencedor
     */
    public Personagem batalhar(Personagem p1, Personagem p2) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.printf("║        ⚔️  BATALHA NA ARENA  ⚔️              ║%n");
        System.out.printf("║   %-18s  VS  %-15s║%n", p1.getNome(), p2.getNome());
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        int rodada = 1;
        while (p1.estaVivo() && p2.estaVivo()) {
            System.out.printf("─── Rodada %d ─────────────────────────────────%n", rodada++);

            // p1 ataca p2
            p1.atacar(p2);
            if (!p2.estaVivo()) break;

            // p2 ataca p1
            p2.atacar(p1);
            if (!p1.estaVivo()) break;

            // Status resumido de ambos
            System.out.printf("  📊 %s: %d/%d HP  |  %s: %d/%d HP%n",
                    p1.getNome(), p1.getVida(), p1.getVidaMaxima(),
                    p2.getNome(), p2.getVida(), p2.getVidaMaxima());
            System.out.println();
        }

        Personagem vencedor  = p1.estaVivo() ? p1 : p2;
        Personagem derrotado = p1.estaVivo() ? p2 : p1;

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.printf("║  🏆 VENCEDOR: %-31s║%n", vencedor.getNome());
        System.out.printf("║  Vida restante: %-29s║%n",
                vencedor.getVida() + "/" + vencedor.getVidaMaxima());
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        return vencedor;
    }

    /**
     * Organiza um torneio eliminatório com todos os participantes.
     * A cada rodada os dois primeiros duelam; o perdedor é eliminado e o
     * vencedor é curado completamente antes do próximo confronto.
     *
     * @param participantes Lista de personagens participantes (mínimo 2)
     */
    public void torneio(ArrayList<Personagem> participantes) {
        if (participantes == null || participantes.size() < 2) {
            System.out.println("❌ São necessários pelo menos 2 participantes para o torneio!");
            return;
        }

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.printf("║       🏆  TORNEIO — LEGENDS OF JAVA  🏆      ║%n");
        System.out.printf("║       Participantes: %-24d║%n", participantes.size());
        System.out.println("╠══════════════════════════════════════════════╣");
        for (Personagem p : participantes) {
            System.out.printf("║  ▸ %-41s║%n", p.getTipo() + " " + p.getNome());
        }
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        int fase = 1;
        while (participantes.size() > 1) {
            System.out.printf("════════════  FASE %d  ════════════%n", fase++);

            Personagem p1 = participantes.get(0);
            Personagem p2 = participantes.get(1);

            Personagem vencedor = batalhar(p1, p2);
            Personagem perdedor = (vencedor == p1) ? p2 : p1;

            participantes.remove(perdedor);

            // Cura completa do vencedor para a próxima batalha
            vencedor.setVida(vencedor.getVidaMaxima());
            vencedor.setMana(vencedor.getManaMaxima());
            System.out.printf("  💊 %s foi curado completamente para o próximo combate!%n%n",
                    vencedor.getNome());

            // Move o vencedor para o final da fila (rodízio)
            participantes.remove(vencedor);
            participantes.add(vencedor);
        }

        Personagem campeao = participantes.get(0);
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║         🌟🌟  GRANDE CAMPEÃO  🌟🌟           ║");
        System.out.printf("║   %-42s║%n", campeao.getTipo() + " " + campeao.getNome());
        System.out.println("║   O mais forte de todos os tempos!           ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }
}
