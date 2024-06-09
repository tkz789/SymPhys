package symphys.symphys.fields.sim;

import symphys.symphys.numerical.Wektor;

public class Simulation {
    boolean gravity_on=false, electrostatics_on=true;
    double t=0;
    public double dt=0.03;
    public Body[] bodies;
    public BodyState[] states;
    public double G=Physics.G, k=Physics.k;

    Simulation() {}

    public Simulation(int n) {
        bodies = new Body[n];
        states = new BodyState[n];
    }

    public void step() {
        t += dt;
        BodyState[] newStates = new BodyState[states.length];
        for (int i=0; i!=states.length; ++i) {
            newStates[i] = states[i].copy();
            Wektor force = new Wektor();
            if (gravity_on) force = force.add(newStates[i].calcForce(states, this::calcGravityForce));
            if (electrostatics_on) force = force.add(newStates[i].calcForce(states, this::calcCoulombForce));
            newStates[i].applyForce(force, dt);
        }
        states = newStates;
    }

    public void dumpPositions() {
        System.out.println("----\nt="+t);
        for (int i=0; i<states.length; ++i) {
            System.out.println(i+": "+states[i].position);
        }
    }

    Wektor calcGravityForce(BodyState o1, BodyState o2) { // sila dzialajaca na o1
        double dist2 = Wektor.dist2(o1.position, o2.position);
        if (dist2==0) return new Wektor();
        double val = G*o1.body.mass*o2.body.mass/dist2;
        return (o2.position.sub(o1.position)).normalize().mul(val);
    }

    Wektor calcCoulombForce(BodyState o1, BodyState o2) {
        double dist2 = Wektor.dist2(o1.position, o2.position);
        if (dist2==0) return new Wektor();
        double val = k*o1.body.charge*o2.body.charge/dist2;
        // System.out.println("DEBUG: val="+val+" dist2="+dist2);
        return (o2.position.sub(o1.position)).normalize().mul(-val);
    }
}
