package com.libgdx.lebmorda.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class CustomCamera extends OrthographicCamera {
	public CustomCamera() {

	}

	public CustomCamera(float viewportWidth, float viewportHeight) {
		super(viewportWidth, viewportHeight);
	}

	BoundingBox left, right, top, bottom = null;

	public void setWorldBounds(int left, int bottom, int width, int height) {

		int top = bottom + height;
		int right = left + width;

		this.left = new BoundingBox(new Vector3(left - 2, 0, 0), new Vector3(left - 1, top, 0));
		this.right = new BoundingBox(new Vector3(right + 1, 0, 0), new Vector3(right + 2, top, 0));
		this.top = new BoundingBox(new Vector3(0, top + 1, 0), new Vector3(right, top + 2, 0));
		this.bottom = new BoundingBox(new Vector3(0, bottom - 1, 0), new Vector3(right, bottom - 2, 0));
	}

	Vector3 lastPosition;

	@Override
	public void translate(float x, float y) {
		lastPosition = new Vector3(position);
		super.translate(x, y);
	}

	public void translateSafe(float x, float y) {
		translate(x, y);
		update();
		ensureBounds();
		update();
	}

	public void ensureBounds() {
		if (isInsideBounds()) {
			position.set(lastPosition);
		}
	}

	private boolean isInsideBounds() {
		if (frustum.boundsInFrustum(left) || frustum.boundsInFrustum(right) || frustum.boundsInFrustum(top)
				|| frustum.boundsInFrustum(bottom)) {
			return true;
		}
		return false;
	}

	public void attemptZoom(float newZoom) {
		this.zoom = newZoom;
		this.snapCameraInView();
	}

	private void snapCameraInView() {
		float halfOfCurrentViewportWidth = ((viewportWidth * zoom) / 2f);
		float halfOfCurrentViewportHeight = ((viewportHeight * zoom) / 2f);

		// Check the vertical camera.
		if (position.x - halfOfCurrentViewportWidth < 0f) // Is going off the left side.
		{
			// Snap back.
			float amountGoneOver = position.x - halfOfCurrentViewportWidth;
			position.x += Math.abs(amountGoneOver);
		} else if (position.x + halfOfCurrentViewportWidth > viewportWidth) {
			// Snap back.
			float amountGoneOver = (viewportWidth - (position.x + halfOfCurrentViewportWidth));
			position.x -= Math.abs(amountGoneOver);
		}

		// Check the horizontal camera.
		if (position.y + halfOfCurrentViewportHeight > viewportHeight) {
			float amountGoneOver = (position.y + halfOfCurrentViewportHeight) - viewportHeight;
			position.y -= Math.abs(amountGoneOver);
		} else if (position.y - halfOfCurrentViewportHeight < 0f) {
			float amountGoneOver = (position.y - halfOfCurrentViewportHeight);
			position.y += Math.abs(amountGoneOver);
		}
	}
}