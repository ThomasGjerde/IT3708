World world;
float MAX_SPEED = 2.0;
float MIN_SEPARATION = 25.0;
void setup() {
  size(700, 700);
  world = new World();
  for (int i = 0; i < 200; i++) {
    world.addBoid(random(width), random(height));
  }
}

void draw() {
  background(50);
  world.updateAll();
}

class World {
  ArrayList<Boid> boids = new ArrayList<Boid>();
  void addBoid(float posX, float posY) {
    boids.add(new Boid(posX, posY));
  }
  void updateAll()
  {
    for (int i = 0; i < boids.size (); i++) {
      boids.get(i).process();
    }
  }
}

class Boid {
  PVector position;
  PVector velocity;

  Boid(float posX, float posY) {
    position = new PVector(posX, posY); 
    velocity = new PVector(0, 0);
  }

  void updatePosition(PVector change) {
    velocity.add(change);
    velocity.limit(MAX_SPEED);
    position.add(velocity);
  }

  void calcForce() {
    //updatePosition(new PVector(0.1,0.5)); //Test
    PVector change = new PVector(0, 0);
    change.add(separation());
    //change.add(alignment());
    //change.add(cohesion());
    updatePosition(change);
  }

  void process() {
    calcForce();
    wrapAround();
    drawBoid();
  }
  void drawBoid() {
    drawArrow(position.x, position.y, 8, velocity.heading());
  }
  void drawArrow(float cx, float cy, int len, float angle) {
    pushMatrix();
    translate(cx, cy);
    rotate(angle);
    stroke(255);
    line(len, 0, len - 8, -8);
    line(len, 0, len - 8, 8);
    popMatrix();
  }
  void wrapAround() {
    if (position.x < 0) {
      position.x = width;
    } 
    if (position.x > width) {
      position.x = 0;
    }
    if (position.y < 0) {
      position.y = height;
    }
    if (position.y > height) {
      position.y = 0;
    }
  }
  PVector separation() {
    PVector allChanges = new PVector(0, 0, 0);
    int totalChanges = 0;
    for (Boid boid : world.boids) {
      if (this != boid && this.position.dist(boid.position) < MIN_SEPARATION) {
        PVector change = PVector.sub(this.position, boid.position);
        change.normalize();
        change.div(this.position.dist(boid.position));
        totalChanges++;
        allChanges.add(change);
      }
    }
    if (totalChanges > 0) {
      allChanges.div(totalChanges);
    } 
    return allChanges;
  }
}

