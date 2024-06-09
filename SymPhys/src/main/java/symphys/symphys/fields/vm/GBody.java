package symphys.symphys.fields.vm;

import symphys.symphys.fields.graphics.LeftPaneForm;
import symphys.symphys.fields.sim.BodyState;
import symphys.symphys.numerical.Wektor;

public class GBody {
    private final double mass, charge;
    private final Wektor position, velocity;
    private final boolean moveableX, moveableY;

    public GBody(LeftPaneForm leftPaneForm) {
        mass = leftPaneForm.massField.getVal();
        charge = leftPaneForm.chargeField.getVal();
        position = new Wektor(leftPaneForm.xField.getVal(), leftPaneForm.yField.getVal());
        velocity = new Wektor(leftPaneForm.vxField.getVal(), leftPaneForm.vyField.getVal());
        moveableX = !leftPaneForm.xLocked.isSelected();
        moveableY = !leftPaneForm.yLocked.isSelected();
    }

    public GBody(BodyState state) {
        mass = state.body.mass;
        charge = state.body.charge;
        position = state.position;
        velocity = state.velocity;
        moveableX = state.body.movableX;
        moveableY = state.body.movableY;
    }

    public double get_mass() {return mass;}
    public double get_charge() {return charge;}
    public Wektor get_position() {return position;}
    public Wektor get_velocity() {return velocity;}
    public boolean get_moveableX() {return moveableX;}
    public boolean get_moveableY() {return moveableY;}
}
