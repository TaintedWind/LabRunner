package player;

import item.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import enemy.AI;
import engine.ObjectList;
import engine.Physics;
import engine.Timer;


public class Player extends Physics {

	Image defaultTexture;
	Image leftFacingTexture;
	Image rightFacingTexture;
	
	Color skinColor = Color.white;
	Timer animationTimer = new Timer();


	public String walkingDir, facingDir;
	public double health = 100;
	public int jumpHeight;
	public boolean isMoving, isJumping;

	public Player(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 48;
		this.H = 96;
		this.health = 100;
		this.walkingDir = null;
		this.isMoving = false;
		this.isJumping = false;
		this.dx = 0.1;

		try {
			defaultTexture = new Image("player.png", false, Image.FILTER_NEAREST);
			leftFacingTexture = new Image("walking_left.png", false, Image.FILTER_NEAREST);
			rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		System.out.println("Creating new player");

	}

	public void update() {

		hitbox.setBounds((int) X, (int) Y, W, H);
		topHitbox.setBounds((int) X, (int) Y, W, H / 3);
		middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
		bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);

		if (facingDir == "left") {
			range.setBounds((int) X - 50, (int) Y, 75, H);
		} else {
			range.setBounds((int) X + 25, (int) Y, 75, H);
		}

		gravity();
		velocity();
		checkForMovement();
		
		animationTimer.updateTimer();

	}

	@Override
	public void health(double amount, Object attacker) {

		if (amount < 0) {
			skinColor = Color.red;
		}

		//this.health += amount;

		if (this.health < 0) {
			this.health = 0;
		} else if (this.health > 100) {
			this.health = 100;
		}

	}

	public void checkForMovement() {

		if (database.GlobalVariables.A && isCollidingWithRightSide() == false) {
			this.X -= 0.3 * database.GlobalVariables.deltaTime;
			this.walkingDir = "left";
			this.facingDir = "left";
			this.isMoving = true;
		} else if (database.GlobalVariables.D && isCollidingWithLeftSide() == false) {
			this.X += 0.3 * database.GlobalVariables.deltaTime;
			this.walkingDir = "right";
			this.facingDir = "right";
			this.isMoving = true;
		} else {
			this.walkingDir = null;
			this.isMoving = false;
		}

		this.X += this.dx * database.GlobalVariables.deltaTime;
		this.Y += fallSpeed;

	}

	public void reset() {
		Inventory.dropInventory();
		health = 100;
		fallHeight = 0;
		fallSpeed = 0;
		dy = 0;
		dx = 0;
		
	}

	public void draw(Graphics g) {

		g.setColor(skinColor);

		if (walkingDir == null) {
			g.drawImage(defaultTexture, (int)X, (int)Y, null);
		} else if (walkingDir == "left") {
			g.drawImage(leftFacingTexture, (int)X, (int)Y, null);
		} else if (walkingDir == "right") {
			g.drawImage(rightFacingTexture, (int)X, (int)Y, null);
		}
		
		skinColor = Color.white;
		g.setColor(Color.white);

		/*g.setColor(Color.red);
		g.drawRect(this.topHitbox.x, this.topHitbox.y, this.topHitbox.width, this.topHitbox.height);
		g.setColor(Color.green);
		g.drawRect(this.middleHitbox.x, this.middleHitbox.y, this.middleHitbox.width, this.middleHitbox.height);
		g.setColor(Color.blue);
		g.drawRect(this.bottomHitbox.x, this.bottomHitbox.y, this.bottomHitbox.width, this.bottomHitbox.height);
		g.drawRect(range.x, range.y, range.width, range.height);
		g.setColor(Color.white);*/

	}

}