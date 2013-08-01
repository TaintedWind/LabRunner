package enemy;

import java.awt.Rectangle;

import org.lwjgl.util.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Player;
import engine.ObjectList;
import engine.Physics;
import engine.Timer;

/*this class defines all the movements that are possible for enemies (including those for land and air)
 * Enemies will override the update method to use only the appropriate methods
 */

public class AI extends Physics {
	
	int damage;
	int delay;
	double health, maxHealth;
	Image defaultTexture;
	String state;
	Rectangle cliffDetection = new Rectangle();
	Point target, t1, t2;
	Color skinColor = Color.white;
	Timer attackTimer, idleTimer;

	public void update() {
		
		if (attackTimer == null) {
			attackTimer = new Timer();
		}
		
		if (idleTimer == null) {
			idleTimer = new Timer();
		}
		
		attackTimer.updateTimer();
		idleTimer.updateTimer();
	
		//update hitboxes
		hitbox.setBounds((int) this.X, (int) this.Y, this.W, this.H);
		bottomHitbox.setBounds((int) this.X, (int) this.Y + (this.H / 2), this.W, this.H / 2);
		range.setBounds((int)X - 250, (int)Y - 50, 550, H + 50);
		cliffDetection.setBounds((int)X - 50, (int)Y + H + 250, W + 100, H + 100);

		//call gravity();
		gravity();
		velocity();
		
		if (hitbox.intersects(ObjectList.player.hitbox)) {
			attack(ObjectList.player);
		}

		if (range.contains(ObjectList.player.hitbox) && hitbox.contains(ObjectList.player.hitbox) == false || state == "hostile") {
			followPlayer();
		} else {
			doIdleMovements();
		}
		
	}
	
	public void followPlayer() {
		if (isCollidingWithGround()) {
			if (ObjectList.player.X > X) {
				X += 0.2 * database.GlobalVariables.deltaTime;
			} else {
				X -= 0.2 * database.GlobalVariables.deltaTime;
			}
		}
	}

	public void attack(Object target) {
		
		if (attackTimer.getTime() > 1000) {
			System.out.println(this+ " is attacking "+target);
			((Physics)target).knockBack(this, 0.01, -0.01);
			((Physics) target).health(-this.damage, this);	
			attackTimer.reset();
		}

	}

	@Override
	public void health(double amount, Object attacker) {
		
		//System.out.println("Attacker: "+attacker);
		
		if (attacker == ObjectList.player) {
			state = "hostile";
		}
		
		if (amount < 0) {
			skinColor = Color.red;
		}
		
		//this.health += amount;
		
		if (this.health > maxHealth) {
			this.health = maxHealth;
		} else if (this.health < 0 || this.health == 0) {
			this.health = 0;
			this.delete();
		}
	}

	public void doIdleMovements() {
		
		if (idleTimer.getTime() > delay * 1000 && delay > 3) {
			if (target == t1) {
				target = t2;
			} else {
				target = t1;
			}
			
			idleTimer.reset();
		}
		
		if (target == t2 && target.getX() > X) {
			X += 0.2 * database.GlobalVariables.deltaTime;
		} else if (target == t1 && target.getX() < X) {
			X -= 0.2 * database.GlobalVariables.deltaTime;
		} else {
			
		}
		
	}
	
	public void delete() {
		ObjectList.enemies.remove(this);
	}

	public void draw(Graphics g) {

		g.setColor(skinColor);
		g.drawImage(this.defaultTexture, (int) this.X, (int) this.Y, null);


		g.setColor(Color.gray);
		g.fillRect((int)X, (int)Y - 10, 50, 5);
		g.setColor(Color.green);
		g.fillRect((int)X, (int)Y - 10, (float) (health * (50 / maxHealth)), 5);
		
		skinColor = Color.white;
		g.setColor(Color.white);

		g.setColor(Color.red);
		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		g.setColor(Color.blue);
		g.drawRect(range.x, range.y, range.width, range.height);
		g.setColor(Color.green);
		g.drawRect(cliffDetection.x, cliffDetection.y, cliffDetection.width, cliffDetection.height);
	}

}
