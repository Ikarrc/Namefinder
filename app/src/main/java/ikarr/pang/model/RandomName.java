package ikarr.pang.model;

public class RandomName {
    private Persona npc;

    public RandomName(Persona npc) {
        this.npc = npc;
        randomFromAncestry(npc);
    }

    public void randomFromAncestry(Persona npc) {
        switch (npc.getAncestry()) {
            case "Dwarf":
                new RandomDwarfName(npc);
                break;
            case "Elf":
                new RandomElfName(npc);
                break;
            case "Gnome":
                new RandomGnomeName(npc);
                break;
            case "Goblin":
                new RandomGobelinName(npc);
                break;
            case "Halfling":
                new RandomHalflingName(npc);
                break;
            /*case "Hobegoblin":
                new RandomDwarfName(npc);
                break;
            case "Human":
                new RandomDwarfName(npc);
                break;
            case "Leshee":
                new RandomDwarfName(npc);
                break;
            case "Lizardfolk":
                new RandomDwarfName(npc);
                break;
            case "Orc":
                new RandomDwarfName(npc);
                break; */
            default:
                break;
        }
    }
}
