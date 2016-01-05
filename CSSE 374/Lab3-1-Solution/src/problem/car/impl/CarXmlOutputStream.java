package problem.car.impl;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import problem.car.api.IBody;
import problem.car.api.ICar;
import problem.car.api.IEngine;
import problem.car.api.IWheel;
import problem.car.visitor.api.*;

public class CarXmlOutputStream extends FilterOutputStream {
	private IVisitor visitor;

	public CarXmlOutputStream(OutputStream out) throws IOException {
		super(out);
		this.visitor = new Visitor();

		this.setPreVisitCar();
		this.setPostVisitICar();
		this.setVisitBody();
		this.setVisitEngine();
		this.setVisitWheeel();
	}

	public void write(ICar c) {
		ITraverser t = (ITraverser) c;
		t.accept(this.visitor);
	}
	
	private void write(String m) {
		try {
			super.write(m.getBytes());
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	private void setPreVisitCar() {
		this.visitor.addVisit(VisitType.PreVisit, ICar.class, (ITraverser t) -> {
			ICar c = (ICar) t;
			String line = String.format("<car vin=\"%s\" make=\"%s\" model=\"%s\">%n",
					c.getVIN(),
					c.getMake(),
					c.getModel());

			write(line);
		});

	}

	private void setPostVisitICar() {
		IVisitMethod command = new IVisitMethod() {
			@Override
			public void execute(ITraverser t) {
				write("</car>\n");
			}
		};
		this.visitor.addVisit(VisitType.PostVisit, ICar.class, command);
	}

	
	private void setVisitBody() {
		this.visitor.addVisit(VisitType.Visit, IBody.class, (ITraverser t) -> {
			IBody b = (IBody) t;

			String line = String.format("<body type=\"%s\" material=\"%s\" />%n",
							b.getType(),
							b.getMaterial());
					write(line);
			});
	}

	private void setVisitEngine() {
		this.visitor.addVisit(VisitType.Visit, IEngine.class, (ITraverser t) -> {
			IEngine e = (IEngine) t;

			String line = String.format("<engine cylinder=%d capacity=%.2f />%n",
					e.getCylinder(),
					e.getCapacity());
			write(line);
		});
	}


	private void setVisitWheeel() {
		this.visitor.addVisit(VisitType.Visit, IWheel.class, (ITraverser t) -> {
			IWheel w = (IWheel) t;

			String line = String.format("<wheel vendor=\"%s\" radius=%.2f width=%.2f />%n",
					w.getVendor(),
					w.getRadius(),
					w.getWidth());
			write(line);
		});
	}
}
