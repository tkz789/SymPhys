package symphys.symphys.fields.sim;

import java.util.function.BiFunction;

public class BodyState {
    public Body body;
    public Wektor position=new Wektor(), velocity =new Wektor();

    public BodyState(Body body) {
        this.body = body;
    }

    BodyState copy() {
        BodyState r = new BodyState(body);
        r.position = position;
        r.velocity = velocity;
        return r;
    }

    Wektor calcForce(BodyState[] states, BiFunction<BodyState, BodyState, Wektor> f) {
        Wektor r = new Wektor();
        for (BodyState o : states) {
            if (o.body==this.body) continue;
            r = r.add(f.apply(this, o));
        }
        return r;
    }

    void applyForce(Wektor force, double dt) {
        Wektor dv = force.mul(dt/body.mass);
        // System.out.println("DEBUG: mass="+body.mass+" dx="+velocity.mul(dt).add(dv.mul(dt/2)));
        if (!body.movableX) velocity = velocity.castY();
        if (!body.movableY) velocity = velocity.castX();
        position = position.add(velocity.mul(dt)).add(dv.mul(dt/2));
        velocity = velocity.add(dv);
    }
}
