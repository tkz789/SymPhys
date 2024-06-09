package symphys.symphys.fields.vm;

import symphys.symphys.fields.sim.BodyState;
import symphys.symphys.fields.sim.Simulation;

import java.util.ArrayList;
import java.util.List;

public class GSimState {
    public List<GBody> gBodies = new ArrayList<>();

    public GSimState(Simulation sim) {
        for (BodyState state: sim.states) {
            gBodies.add(new GBody(state));
        }
    }
}
