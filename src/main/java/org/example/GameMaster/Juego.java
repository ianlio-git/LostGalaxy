package org.example.GameMaster;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Exception.*;
import org.example.Nave.TiposDeNaves.*;
import org.example._MVC.Views.GameBeginView;
import org.example.Enums.Dificultad;
import org.example.MapaEstelar.MapaEstelar;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Enums.TipoDeNave;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example._MVC.Views.MapaEstelarView;
import org.example._MVC.Views.MessageView;

public class Juego {
    private static int turno = 0;
    private boolean turnoExtra =true;
    private static Juego instanciaJuego;
    private MapaEstelar mapaEstelar;
    private Jugador jugador;
    private String mensajeRecibido;
    private int cantidadSistemasEstelares;
    private Juego() {
        this.mapaEstelar = MapaEstelar.getInstancia();
    }


    public static Juego getInstancia() {
        if (instanciaJuego == null) {
            instanciaJuego = new Juego();
        }
        return instanciaJuego;
    }
    public void iniciarJuego(String nombreDelJugador, TipoDeNave naveJugador, int cantidadSistemasEstelaresEstablecida, Dificultad dificultad) throws JuegoIniciadoException {
        double uadeCoinsJugador = 1000000;
        cantidadSistemasEstelares = cantidadSistemasEstelaresEstablecida;
        if (jugador == null) {
            mapaEstelar.crearMapaEstelar(dificultad,cantidadSistemasEstelares);
            SistemaEstelar sistemaInicial = mapaEstelar.obtenerSistemaEstelar("SIST-0");
            Planeta planetaInicial = sistemaInicial.obtenerPlanetaNeutral();
            switch (naveJugador) {
                case NAVE_AEGIS:
                    NaveAegis naveAegis = new NaveAegis();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveAegis,planetaInicial,sistemaInicial);
                    break;
                case NAVE_SWIFT:
                    NaveSwift naveSwift = new NaveSwift();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveSwift,planetaInicial,sistemaInicial);
                    break;
                case NAVE_PHANTOM:
                    NavePhantom navePhantom = new NavePhantom();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, navePhantom,planetaInicial,sistemaInicial);
                    break;
                case NAVE_TITAN:
                    NaveTitan naveTitan = new NaveTitan();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveTitan,planetaInicial,sistemaInicial);
                    break;


            }
        } else {
            throw new JuegoIniciadoException("El juego ya fue iniciado");
        }
    }


    public void indicarRumboANuevoSistema(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, SistemaInvalidoException, AtravesandoCinturonException, NavePhantomException, CambiandoDeSistemaExeption {
        pasarTurno();
        if(mapaEstelar.verificarExistenciaDeSistemaEstelar(codigoDeSistema)){
            if (!jugador.getSistemaActual().mostrarNombre().equals(codigoDeSistema)) {
                SistemaEstelar sistemaNuevo = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema);
                jugador.comenzarViajeANuevoSistema(sistemaNuevo);//pase los metodos que devolvían string a void
                if(sistemaNuevo.tieneCinturonAsteroides()) {
                    jugador.setPosicionEnElEspacio("En cinturon de asteroides");
                }else{
                    jugador.setPosicionEnElEspacio("En el espacio");
                }
            }
        }
        else {
            throw new SistemaInvalidoException("Ingrese un sistema valido");
        }
    }



    public void comprarArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException, LimiteDeArmas, SinCombustibleNiUadeCoins {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(!jugador.getNave().limiteDeArmas()){
                throw new LimiteDeArmas("Ya haz comprado 2 armas.");
            }
            if(planeta.ingresarAlMercado().accionDeComprarArma(jugador, tipoDeArma)){

            }
            else {
                throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
            }
        }
    }

    public void comprarCombustible(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException, TanqueLlenoException, SinCombustibleNiUadeCoins {
        Planeta planeta = irAlShopping();
        if (!jugador.getNave().getTanque().tanqueLleno()) {
            if (!planeta.ingresarAlMercado().accionDeComprarCombustible(jugador, cantidad)) {
                throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
            }
        }
        else {
            throw new TanqueLlenoException("Tanque de combustible lleno");
        }
    }

    public void recargarEscudo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException, EscudoLleno, SinCombustibleNiUadeCoins {
        Planeta planeta = irAlShopping();
        if(!jugador.getNave().getEscudo().escudoLleno()){
            if(planeta!=null){
                if(planeta.ingresarAlMercado().accionDeRecargarEscudo(jugador,cantidad)){
                }else{
                    throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
                }
            }
        }else{
            throw new EscudoLleno("Tu escudo esta lleno, compra mas escudo maximo para añadir más.");
        }
    }



    public void venderArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, ArmaNoEncontradaException, PlanetaNoEncontradoException, SinCombustibleNiUadeCoins {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeVenderArma(jugador,tipoDeArma)){
                // finDelJuego("Vendiste el arma con exito!"); hay que pasar esto a la view
            }else{
                throw new ArmaNoEncontradaException("No posees el arma que quieres vender.");
            }
        }
    }
    public void comprarEscudoMaximo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException, SinCombustibleNiUadeCoins {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeComprarEscudoMaximo(jugador,cantidad)){
                // finDelJuego("Compraste escudo con exito"); hay que pasar esto a la view
            }else{
                throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
            }
        }
    }

    private Planeta irAlShopping() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException, SinCombustibleNiUadeCoins {
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaNeutral();
        if(jugador.getPlanetaActual() != planeta){
            if (jugador.puedoViajar(planeta)) {
                jugador.setPlanetaActual(planeta);
                jugador.setPosicionEnElEspacio(planeta.getCodigoDePlaneta());
                jugador.viajeAPlaneta(planeta);
                pasarTurno();
                return planeta;
            }else {
                pasarTurno();
                throw new SinCombustibleNiUadeCoins("Te quedaste sin combustible");
            }
        }
        return jugador.getPlanetaActual();
    }

    public Jugador realizarAccionDeReparacion() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException {
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaAliado();

        if(planeta==null){
            throw new PlanetaNoEncontradoException("No se pudo encontrar el planeta.");
        }else{
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                planeta.repararNaveAliada(jugador);
                pasarTurno();

            } else {
                throw new CombustibleInsuficienteException("Te quedaste sin combustible");
            }
        }

        return jugador;

    }

    public void realizarAccionDeInformacion() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException, UadeCoinsInsuficientesException {

        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaAliado();
        if(planeta==null){
            throw new PlanetaNoEncontradoException("No se pudo encontrar el planeta.");
        }else{
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                planeta.obtenerInformacion(jugador);
                pasarTurno();
            }
            else {
                throw new CombustibleInsuficienteException("Te quedaste sin combustible");
            }
        }

    }

    public double atacarPlanetaHostil(String codigoDePlaneta) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException, NaveEnemigaDestuidaException, NaveEnemigaDestuidaException, AtacarSoloPlanetasHostiles {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaHostil(codigoDePlaneta);
        NavePirata navePirata = planeta.getNavePirata();
        System.out.println(navePirata.poderAtaque());
        double cantDeUadeCoinsInicial = jugador.getUadeCoins();
        if(planeta==null){
            throw new PlanetaNoEncontradoException("No se pudo encontrar el planeta.");
        }else {
            if(planeta.getCodigoDePlaneta().contains("ENE")){
                if (jugador.puedoViajar(planeta)) {
                    jugador.viajeAPlaneta(planeta);
                    planeta.combate(this.jugador);
                    double cantDeUadeCoinsFinales = jugador.getUadeCoins();
                    jugador.getSistemaActual().quitarPlaneta(planeta);

                    throw new NaveEnemigaDestuidaException("La nave enemiga fue destruida. ¡Muy bien!, Por desgracia no has encontrado el tesoro, sigue intentado! \n UadeCoins obtenidas: " + (cantDeUadeCoinsFinales - cantDeUadeCoinsInicial));
                } else {
                    throw new CombustibleInsuficienteException("Te quedaste sin combustible");
                }
            }
            else {
                throw new AtacarSoloPlanetasHostiles("Que malvados planes tienes? Solo ataca planetas enemigos.");
            }
        }

    }


    public MessageView messageToView() {
        return new MessageView(mensajeRecibido);
    }

    private void pasarTurno(){
        if (jugador.getNave().soyNaveTipo() == TipoDeNave.NAVE_TITAN) {
            if (turnoExtra) {
                turnoExtra = false;
            } else {
                turnoExtra = true;
                ++this.turno;
            }
        }else{
            ++this.turno;
        }

    }

    public Jugador getJugador() {
        return jugador;
    }

    public GameBeginView toViewgameBegin(){
        return new GameBeginView(jugador.getNombre(),jugador.getNave().soyNaveTipo(),mapaEstelar.getSistemasEstelares().size(),mapaEstelar.getDificultad(),turno);
    }

    public MapaEstelar getMapaEstelar() {
        return mapaEstelar;
    }



    public void reiniciarJuego() {
        jugador.getNave().vaciarArmamentos();
        jugador.getNave().reestablecerVida();
        jugador.setUadeCoins(1000);
        SistemaEstelar sistemaInicial = mapaEstelar.obtenerSistemaEstelar("SIST-0");
        Planeta planetaInicial = sistemaInicial.obtenerPlanetaNeutral();
        jugador.setSistemaActual(sistemaInicial);
        jugador.setPlanetaActual(planetaInicial);
        jugador.setPosicionEnElEspacio(planetaInicial.getCodigoDePlaneta());
        jugador.getNave().getEscudo().escudoAcero();
        jugador.getNave().getTanque().tanqueADefault();
        turno = 0;
    }
}