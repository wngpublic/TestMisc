public class SShapes {
	static class Rectangle {
		public int x1, y1, x2, y2, w, h;
		public Rectangle(int x1, int y1, int x2, int y2) {
			/*
			 * 		x1,y1
			 *  	+-------------------+
			 *  	|					|
			 *  	|					|
			 *  	|				 	|
			 *  	+-------------------+ x2,y2
			 *  
			 */
			if(x2 > x1) {
				this.x1 = x2;
				this.x2 = x1;
			} else {
				this.x1 = x1;
				this.x2 = x2;
			}
			if(y2 > y1) {
				this.y1 = y2;
				this.y2 = y1;
			} else {
				this.y1 = y1;
				this.y2 = y2;
			}
			this.w = Math.abs(x2 - x1);
			this.h = Math.abs(y2 - y1);
		}
		public boolean intersects(Rectangle r) {
			return false;
		}
	}
}
