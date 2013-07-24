package enemy;

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
	double health;
	Image icon;

	public boolean inScene;
	Object player;

	String State;

	public void Update() {
		if (this.inScene) {

			this.hitbox.setBounds((int) this.X, (int) this.Y, this.W, this.H);
			this.bottomHitbox.setBounds((int) this.X, (int) this.Y + (this.H / 2), this.W, this.H / 2);

			EntityCollisionCheck();
			Gravity();

			if (this.State == "idle") {
				IdleMovements();
			} else {
				FollowPlayer();
			}

		}

	}
	
	//AI jumping (including knockback when hit, that is what 'dir' is for)
	public void Jump(String dir) {
		if (dir == "left") {
			Y += 10;
			X--;
		} else {
			Y += 10;
			X++;
		}
	}
	
	public void FollowPlayer() {
		if (ObjectList.player.X > X) {
			X += 0.2 * settings.GlobalVariables.Delta;
		} else {
			X -= 0.2 * settings.GlobalVariables.Delta;;
		}
	}

	public void Attack(Object target) {

		//the parameter for attack is the player that is colliding with this instance

		if (Timer.getCurrentTime() > 300 && Timer.getCurrentTime() < 400) {
			System.out.println(this+ " is attacking "+target);
			((Player) target).Health(-this.damage);
		}

	}

	public void EntityCollisionCheck() {
		if (hitbox.intersects(ObjectList.player.hitbox)) {
			Attack(ObjectList.player);
		}
	}

	@Override
	public void Health(double amount) {
		this.health += amount;
		if (this.health > 100) {
			this.health = 100;
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

		
	}

}
