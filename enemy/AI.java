package enemy;

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
	double health, maxHealth;
	Image defaultTexture;
	String state;
	
	Color skinColor = Color.white;
	
	Timer attackTimer, idleTimer;

	public void Update() {
		
		if (attackTimer == null) {
			attackTimer = new Timer();
		}
		
		if (idleTimer == null) {
			
		}
		
		attackTimer.updateTimer();
		
		

		hitbox.setBounds((int) this.X, (int) this.Y, this.W, this.H);
		bottomHitbox.setBounds((int) this.X, (int) this.Y + (this.H / 2), this.W, this.H / 2);
		range.setBounds((int)X - 300, (int)Y - 50, 650, H + 50);

		Gravity();
		
		if (hitbox.intersects(ObjectList.player.hitbox)) {
			Attack(ObjectList.player);
		}

		IdleMovements();
		
	}
	
	public void FollowPlayer() {
		if (range.contains(ObjectList.player.hitbox) && hitbox.contains(ObjectList.player.hitbox) == false) {
			if (ObjectList.player.X > X) {
				X += 0.2 * settings.GlobalVariables.Delta;
			} else {
				X -= 0.2 * settings.GlobalVariables.Delta;				
			}
		}
	}

	public void Attack(Object target) {
		
		if (attackTimer.getTime() > 1000) {
			System.out.println(this+ " is attacking "+target);
			((Player) target).Health(-this.damage);
			attackTimer.reset();
		}

	}

	@Override
	public void Health(double amount) {
		
		if (amount < 0) {
			skinColor = Color.red;
		} else if (amount > 0){

		}
		
		this.health += amount;
		
		if (this.health > maxHealth) {
			this.health = maxHealth;
		} else if (this.health < 0 || this.health == 0) {
			this.health = 0;
			this.delete();
		}
	}

	public void IdleMovements() {
		
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
	}

}
