package player;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;
import engine.Physics;
import item.Item;
import enemy.AI;


public class Player extends Physics {

	Image defaultTexture;
	Image leftFacingTexture;
	Image rightFacingTexture;

	
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
		
		try {
			defaultTexture = new Image("player.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			leftFacingTexture = new Image("walking_left.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			rightFacingTexture = new Image("walking_right.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		System.out.println("Creating new player");
		
	}

	public void Update() {

			hitbox.setBounds((int) X, (int) Y, W, H);
			topHitbox.setBounds((int) X, (int) Y, W, H / 3);
			middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
			bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
			
			if (facingDir == "left") {
				range.setBounds((int) X - 50, (int) Y, 75, H);
			} else {
				range.setBounds((int) X + 25, (int) Y, 50, H);				
			}

			Move();
			Gravity();

	}

	@Override
	public void Health(double amount) {

		this.health += amount;

		if (this.health < 0) {
			this.health = 0;
			Reset();
		} else if (this.health > 100) {
			this.health = 100;
		}
		
	}
	
	//swings arm or equipped item, called continuously when left mouse button is held down
	public void Swing() {
		
		if (Inventory.selectedItem != null) {
			((Item)Inventory.selectedItem).Swing();
		}
		
	}
	
	//called once when left mouse button is clicked
	public void Attack() {
		
		if (getCollidingEnemy(range) != null) {
			if (Inventory.selectedItem != null) {
				((AI)getCollidingEnemy(range)).Health(-((Item)Inventory.selectedItem).damage);
			} else {
				((AI)getCollidingEnemy(range)).Health(-1);
			}
			
			//((AI)getCollidingEnemy(range)).defaultTexture.setColor(Color.red);
		}
	}

	public void Move() {

		if (settings.GlobalVariables.A && isCollidingWithRightSide() == false) {
			this.dx = -0.3;
			this.walkingDir = "left";
			this.facingDir = "left";
			this.isMoving = true;
		} else if (settings.GlobalVariables.D && isCollidingWithLeftSide() == false) {
			this.dx = 0.3;
			this.walkingDir = "right";
			this.facingDir = "right";
			this.isMoving = true;
		} else {
			this.dx = 0;
			this.walkingDir = null;
			this.isMoving = false;
		}

		this.X += this.dx * settings.GlobalVariables.Delta;
		this.Y += dy;

	}

	public void Reset() {
		Inventory.dropInventory();
		health = 100;
		fallHeight = 0;
		fallSpeed = 0;
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.white);

		if (walkingDir == null) {
			g.drawImage(defaultTexture, (int)X, (int)Y, null);
		} else if (walkingDir == "left") {
			g.drawImage(leftFacingTexture, (int)X, (int)Y, null);
		} else if (walkingDir == "right") {
			g.drawImage(rightFacingTexture, (int)X, (int)Y, null);
		}

		g.setColor(Color.red);
		g.drawRect(this.topHitbox.x, this.topHitbox.y, this.topHitbox.width, this.topHitbox.height);
		g.setColor(Color.green);
		g.drawRect(this.middleHitbox.x, this.middleHitbox.y, this.middleHitbox.width, this.middleHitbox.height);
		g.setColor(Color.blue);
		g.drawRect(this.bottomHitbox.x, this.bottomHitbox.y, this.bottomHitbox.width, this.bottomHitbox.height);
		g.drawRect(range.x, range.y, range.width, range.height);
		g.setColor(Color.white);
		
	}

}