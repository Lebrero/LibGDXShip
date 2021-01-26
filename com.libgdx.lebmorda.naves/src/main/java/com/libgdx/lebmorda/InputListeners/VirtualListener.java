package com.libgdx.lebmorda.InputListeners;

import com.badlogic.gdx.math.Vector2;

public class VirtualListener {

	// Actor
	private boolean moverIzquierda;
	private boolean moverDerecha;
	private boolean moverArriba;
	private boolean moverAbajo;
	private boolean girarDerecha;
	private boolean girarIzquierda;

	// Camara
	private boolean moverCamaraIzquierda;
	private boolean moverCamaraDerecha;
	private boolean moverCamaraArriba;
	private boolean moverCamaraAbajo;
	private boolean zoomIn;
	private boolean zoomOut;
	
	private boolean scroollZoom;
	

	// Mouse
	private boolean tocarActor;
	private boolean tocarPantalla;
	private boolean tocadoTeclado;

	// Position mouse
	private Vector2 mouseVectorXY = new Vector2();

	// Position screen
	private Vector2 screenVectorXY = new Vector2();

	public boolean isMoverIzquierda() {
		return moverIzquierda;
	}

	public void setMoverIzquierda(boolean moverIzquierda) {
		this.moverIzquierda = moverIzquierda;
	}

	public boolean isMoverDerecha() {
		return moverDerecha;
	}

	public void setMoverDerecha(boolean moverDerecha) {
		this.moverDerecha = moverDerecha;
	}

	public boolean isMoverArriba() {
		return moverArriba;
	}

	public void setMoverArriba(boolean moverArriba) {
		this.moverArriba = moverArriba;
	}

	public boolean isMoverAbajo() {
		return moverAbajo;
	}

	public void setMoverAbajo(boolean moverAbajo) {
		this.moverAbajo = moverAbajo;
	}

	public boolean isTocarPantalla() {
		return tocarPantalla;
	}

	public void setTocarPantalla(boolean tocarPantalla) {
		this.tocarPantalla = tocarPantalla;
	}

	public Vector2 getMouseVectorXY() {
		return mouseVectorXY;
	}

	public void setMouseVectorXY(Vector2 mouseVectorXY) {
		this.mouseVectorXY = mouseVectorXY;
	}

	public boolean isMoverCamaraIzquierda() {
		return moverCamaraIzquierda;
	}

	public void setMoverCamaraIzquierda(boolean moverCamaraIzquierda) {
		this.moverCamaraIzquierda = moverCamaraIzquierda;
	}

	public boolean isMoverCamaraDerecha() {
		return moverCamaraDerecha;
	}

	public void setMoverCamaraDerecha(boolean moverCamaraDerecha) {
		this.moverCamaraDerecha = moverCamaraDerecha;
	}

	public boolean isMoverCamaraArriba() {
		return moverCamaraArriba;
	}

	public void setMoverCamaraArriba(boolean moverCamaraArriba) {
		this.moverCamaraArriba = moverCamaraArriba;
	}

	public boolean isMoverCamaraAbajo() {
		return moverCamaraAbajo;
	}

	public void setMoverCamaraAbajo(boolean moverCamaraAbajo) {
		this.moverCamaraAbajo = moverCamaraAbajo;
	}

	public boolean isZoomOut() {
		return zoomOut;
	}

	public void setZoomOut(boolean zoomOut) {
		this.zoomOut = zoomOut;
	}

	public boolean isZoomIn() {
		return zoomIn;
	}

	public void setZoomIn(boolean zoomIn) {
		this.zoomIn = zoomIn;
	}

	public boolean isTocarActor() {
		return tocarActor;
	}

	public void setTocarActor(boolean tocarActor) {
		this.tocarActor = tocarActor;
	}

	public Vector2 getScreenVectorXY() {
		return screenVectorXY;
	}

	public void setScreenVectorXY(Vector2 screenVectorXY) {
		this.screenVectorXY = screenVectorXY;
	}

	public boolean isTocadoTeclado() {
		return tocadoTeclado;
	}

	public void setTocadoTeclado(boolean tocadoTeclado) {
		this.tocadoTeclado = tocadoTeclado;
	}

	public boolean isGirarIzquierda() {
		return girarIzquierda;
	}

	public void setGirarIzquierda(boolean girarIzquierda) {
		this.girarIzquierda = girarIzquierda;
	}

	public boolean isGirarDerecha() {
		return girarDerecha;
	}

	public void setGirarDerecha(boolean girarDerecha) {
		this.girarDerecha = girarDerecha;
	}

	public boolean isScroollZoom() {
		return scroollZoom;
	}

	public void setScroollZoom(boolean scroollZoom) {
		this.scroollZoom = scroollZoom;
	}


}
