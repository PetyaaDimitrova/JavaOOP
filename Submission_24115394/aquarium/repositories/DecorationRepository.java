package aquarium.repositories;

import aquarium.entities.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DecorationRepository implements Repository{
    //decorations - Collection<Decoration> (unmodifiable)
    private List<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return this.decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        //Returns the first decoration of the given type, if there is. Otherwise, returns

        return decorations.stream().filter(d->d.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
