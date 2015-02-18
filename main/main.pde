import java.text.DecimalFormat;
World world;
float separationWeight = 2.0;
float alignmentWeight = 1.0;
float cohesionWeight = 1.0;
float MAX_SPEED = 2.0;
float MIN_SEPARATION = 20.0;
float RANGE = 70;
float BOID_SIZE = 4;
int BUTTON_SIZE = 40;
int BUTTONS_Y;
int LABEL_FONT_SIZE = 16;
int PLUS_SEPARATION = 150;
int LABEL_SEPARATION = 60;
int MINUS_SEPARATION = 30;
int PLUS_ALIGNMENT = 330;
int LABEL_ALIGNMENT = 230;
int MINUS_ALIGNMENT = 200;
int MINUS_COHESION = 370;
int LABEL_COHESION = 400;
int PLUS_COHESION = 490;
int MINUS_PREDATOR = 550;
int PLUS_PREDATOR = 590;
int MINUS_OBSTACLE = 630;
int PLUS_OBSTACLE = 670;
PFont separationLabel;
PFont alignmentLabel;
PFont cohesionLabel;
DecimalFormat df;
boolean plusSeparationOver = false;
boolean minusSeparationOver = false;
boolean plusAlignmentOver = false;
boolean minusAlignmentOver = false;
boolean plusCohesionOver = false;
boolean minusCohesionOver = false;
boolean plusPredatorOver = false;
boolean minusPredatorOver = false;
boolean plusObstacleOver = false;
boolean minusObstacleOver = false;

public void setup() {
  size(700, 700);
  df = new DecimalFormat();
  df.setMaximumFractionDigits(2);
  BUTTONS_Y = height - 25;
  separationLabel = createFont("Arial",LABEL_FONT_SIZE,true);
  alignmentLabel = createFont("Arial",LABEL_FONT_SIZE,true);
  cohesionLabel = createFont("Arial",LABEL_FONT_SIZE,true);
  
  
  world = new World();
  for (int i = 0; i < 200; i++) {
    world.addBoid(random(width), random(height));
  }
    
}
private void displayText(PFont font,String text,float num,int x, int y){
  textFont(font,LABEL_FONT_SIZE);
  text(text + df.format(num),x,y); 
}
public void update(){
  setAllButtonsFalse();
  if(overCircle(PLUS_SEPARATION,BUTTONS_Y,BUTTON_SIZE)){
   plusSeparationOver = true; 
  }
  if(overCircle(MINUS_SEPARATION,BUTTONS_Y,BUTTON_SIZE)){
   minusSeparationOver = true; 
  }
  if(overCircle(PLUS_ALIGNMENT,BUTTONS_Y,BUTTON_SIZE)){
   plusAlignmentOver = true; 
  }
  if(overCircle(MINUS_ALIGNMENT,BUTTONS_Y,BUTTON_SIZE)){
   minusAlignmentOver = true; 
  }
  if(overCircle(PLUS_COHESION,BUTTONS_Y,BUTTON_SIZE)){
   plusCohesionOver = true;
  }
  if(overCircle(MINUS_COHESION,BUTTONS_Y,BUTTON_SIZE)){
   minusCohesionOver = true; 
  }
  if(overCircle(MINUS_PREDATOR,BUTTONS_Y,BUTTON_SIZE)){
   minusPredatorOver = true; 
  }
  if(overCircle(PLUS_PREDATOR,BUTTONS_Y,BUTTON_SIZE)){
   plusPredatorOver = true; 
  }
  if(overCircle(MINUS_OBSTACLE,BUTTONS_Y,BUTTON_SIZE)){
   minusObstacleOver = true; 
  }
  if(overCircle(PLUS_OBSTACLE,BUTTONS_Y,BUTTON_SIZE)){
   plusObstacleOver = true; 
  }
  
}

public void mousePressed() {
  if (plusSeparationOver) {
    separationWeight += 0.1;
  }
  if(minusSeparationOver){
     separationWeight -= 0.1; 
  }
  if(plusAlignmentOver){
   alignmentWeight += 0.1; 
  }
  if(minusAlignmentOver){
   alignmentWeight -= 0.1; 
  }
  if(plusCohesionOver){
   cohesionWeight += 0.1; 
  }
  if(minusCohesionOver){
   cohesionWeight -= 0.1; 
  }
  if(plusPredatorOver){
   world.addPredator(random(width),random(height)); 
  }
  if(minusPredatorOver){
   if(world.predators.size() > 0){
    world.predators.remove(0);
   } 
  }
  if(plusObstacleOver){
   world.addObstacle(random(width),random(height)); 
  }
  if(minusObstacleOver){
   if(world.obstacles.size() > 0){
    world.obstacles.remove(0); 
   }
  }
}

private void setAllButtonsFalse(){
 plusSeparationOver = false;
  minusSeparationOver = false; 
  plusAlignmentOver = false;
  minusAlignmentOver = false;
  plusCohesionOver = false;
  minusCohesionOver = false;
   plusPredatorOver = false;
 minusPredatorOver = false;
 plusObstacleOver = false;
 minusObstacleOver = false;
}

private boolean overCircle(int x, int y, int diameter) {
  float disX = x - mouseX;
  float disY = y - mouseY;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
  }
}

public void draw() {
  background(50);
  //stroke(0);
  ellipse(PLUS_SEPARATION, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  displayText(separationLabel,"Sep: ", separationWeight,LABEL_SEPARATION,BUTTONS_Y); 
  ellipse(MINUS_SEPARATION, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
    
  ellipse(PLUS_ALIGNMENT, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  displayText(alignmentLabel,"Align: ", alignmentWeight,LABEL_ALIGNMENT,BUTTONS_Y); 
  ellipse(MINUS_ALIGNMENT, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  
  ellipse(PLUS_COHESION, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  displayText(cohesionLabel,"Coh: ", cohesionWeight,LABEL_COHESION,BUTTONS_Y); 
  ellipse(MINUS_COHESION, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  
  ellipse(PLUS_PREDATOR, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  ellipse(MINUS_PREDATOR, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  
    ellipse(PLUS_OBSTACLE, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  ellipse(MINUS_OBSTACLE, BUTTONS_Y, BUTTON_SIZE, BUTTON_SIZE);
  
  update();
  world.updateAll();
}

public class World {
  ArrayList<Boid> boids = new ArrayList<Boid>();
  ArrayList<Predator> predators = new ArrayList<Predator>();
  ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
  public void addBoid(float posX, float posY) {
    boids.add(new Boid(posX, posY));
  }
  public void addPredator(float posX,float posY){
   predators.add(new Predator(posX,posY)); 
  }
  public void addObstacle(float posX,float posY){
   obstacles.add(new Obstacle(posX,posY)); 
  }
  public void updateAll()
  {
    for (int i = 0; i < boids.size (); i++) {
      boids.get(i).process();
    }
    for(int i = 0; i < predators.size(); i++){
     predators.get(i).process(); 
    }
    for(int i = 0; i < obstacles.size(); i++){
     obstacles.get(i).process(); 
    }
  }
}

public class Boid {
  PVector position;
  PVector velocity;

  public Boid(float posX, float posY) {
    position = new PVector(posX, posY); 
    velocity = new PVector(0, 0);
  }

  private void updatePosition(PVector change) {
    velocity.add(change);
    velocity.limit(MAX_SPEED);
    position.add(velocity);
  }

  private void calcForce() {
    PVector change = new PVector(0, 0);
    PVector separation = separation();
    PVector alignment = alignment();
    PVector cohesion = cohesion();
    separation.mult(separationWeight);
    alignment.mult(alignmentWeight);
    cohesion.mult(cohesionWeight);
    change.add(separation);
    change.add(alignment);
    change.add(cohesion);
    change.add(avoidPredator());
    change.add(avoidObstacle());
    updatePosition(change);
  }

  public void process() {
    calcForce();
    wrapAround();
    drawBoid();
  }
  private void drawBoid() {
    drawArrow(position.x, position.y, 4, velocity.heading());
  }
  private void drawArrow(float cx, float cy, int len, float angle) {
    pushMatrix();
    translate(cx, cy);
    rotate(angle);
    stroke(255);
    line(len, 0, len - 4, -4);
    line(len, 0, len - 4, 4);
    popMatrix();
  }
  private void wrapAround() {
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
  private PVector separation() {
    PVector allChanges = new PVector(0, 0, 0);
    int totalChanges = 0;
    for (Boid boid : world.boids) {
      if (this != boid && (this.position.dist(boid.position) - BOID_SIZE) < MIN_SEPARATION) {
        PVector change = PVector.sub(this.position, boid.position);
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
  private PVector alignment() {
    int totalChanges = 0;
    PVector average = new PVector(0, 0);
    for (Boid boid : world.boids) {
      if (boid != this && this.position.dist(boid.position) <= RANGE)
      {
        average.add(boid.velocity);
        totalChanges++;
      }
    }    
    if (totalChanges > 0) {
      average.div(totalChanges);
    }
    return average;
  }
  private PVector cohesion() {
    int totalChanges = 0;
    PVector average = new PVector(0, 0);
    for (Boid boid : world.boids) {
      if (boid != this && this.position.dist(boid.position) <= RANGE) {
        average.add(boid.position);
        totalChanges++;
      }
    }
    if (totalChanges > 0) {
      average.div(totalChanges);
      PVector toTarget = PVector.sub(average,this.position);
      PVector change = PVector.sub(toTarget,this.velocity);
      change.limit(0.05);
      return change;
    }else{
      return new PVector(0,0);
    }
  }
  private PVector avoidPredator(){
    PVector change = new PVector(0,0);
    int totalChange = 0;
   for(Predator predator : world.predators) {
    if(this.position.dist(predator.position) < RANGE){
      PVector fromTarget = PVector.sub(predator.position,this.position);
      fromTarget.rotate(PI);
      change.add(fromTarget);
    }
   }
  if(totalChange > 0){
   change.div(totalChange);
  } 
  return change;
  }
  private PVector avoidObstacle(){
   PVector change = new PVector(0,0);
  int totalChange = 0;
 for(Obstacle obstacle : world.obstacles){
  if(PVector.sub(this.position,obstacle.position).x > -5 && PVector.sub(this.position,obstacle.position).x < 5){
  change.add(10,0,0);
  }else if(PVector.sub(this.position,obstacle.position).y > -5 && PVector.sub(this.position,obstacle.position).y < 5){
  change.add(0,10,0);
  }  
 }
 return change;
  }
}
public class Predator{
  PVector position;
  PVector velocity;

  public Predator(float posX, float posY) {
    position = new PVector(posX, posY); 
    velocity = new PVector(0, 0);
  }
    public void process() {
    drawPredator();
  }
  private void drawPredator() {
    drawArrow(position.x, position.y, 4, velocity.heading());
  }
  private void drawArrow(float cx, float cy, int len, float angle) {
    pushMatrix();
    translate(cx, cy);
    rotate(angle);
    stroke(255,0,0);
    line(len, 0, len - 4, -4);
    line(len, 0, len - 4, 4);
    popMatrix();
    stroke(255);
  }
}

public class Obstacle{
 PVector position;
public Obstacle(float posX, float posY){
 position = new PVector(posX,posY);
} 
public void process(){
  drawObstacle();
}
private void drawObstacle(){
 rect(position.x,position.y,4,4); 
}
}

