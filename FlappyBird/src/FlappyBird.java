import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    //player
    int playerStartPostX = frameWidth / 8;
    int PlayerStartPostY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    Player player;

    int pipeStartPosX = frameWidth;
    int PipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    int pipesPassed = 0;

    ArrayList<Pipe> pipes;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean isGameOver = false;

    int score = 0;
    JLabel scoreLabel;
    JLabel gameOverLabel;

    JLabel pressRLabel;

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
        setLayout(new BorderLayout());

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPostX, PlayerStartPostY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();

        score = 0;
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel, BorderLayout.NORTH);

        // Buat panel baru untuk mengelompokkan gameOverLabel dan pressRLabel
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        gameOverPanel.setOpaque(false);

        gameOverLabel = new JLabel("\nGame Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVisible(false);
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        pressRLabel = new JLabel("Press R to Restart the Game");
        pressRLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pressRLabel.setForeground(Color.WHITE);
        pressRLabel.setHorizontalAlignment(JLabel.CENTER);
        pressRLabel.setVisible(false);
        gameOverPanel.add(pressRLabel, BorderLayout.SOUTH);

        add(gameOverPanel, BorderLayout.CENTER);

        this.gameOverLabel = gameOverLabel;
        this.pressRLabel = pressRLabel;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeigth(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeigth(), null);
        }
    }

    public void placePipes() {
        int randomPipePosY = (int) (PipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move() {
        if (!isGameOver) {
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0));

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setPosX(pipe.getPosX() + pipe.getVelocityY());

                if (player.intersects(pipe)) {
                    isGameOver = true;
                    gameLoop.stop();
                    gameOverLabel.setVisible(true); // Saat permainan berakhir, tampilkan "Game Over"
                    pressRLabel.setVisible(true);
                    break;
                }
            }

            for (Pipe pipe : pipes) {
                if (pipe.getPosX() + pipe.getWidth() < player.getPosX() && !pipe.isPassed()) {
                    pipe.setPassed(true);
                    pipesPassed++;
                    if (pipesPassed == 2) {
                        score++;
                        updateScoreLabel(); // Memperbarui skor setiap kali pemain melewati 2 pipa
                        pipesPassed = 0;
                    }
                }
            }

            if (player.getPosY() + player.getHeigth() >= frameHeight) {
                isGameOver = true;
                gameLoop.stop();
                gameOverLabel.setVisible(true); // Saat permainan berakhir, tampilkan "Game Over"
                pressRLabel.setVisible(true);
            }
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        } else if (isGameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void restartGame() {
        player.setPosX(playerStartPostX);
        player.setPosY(PlayerStartPostY);
        isGameOver = false;
        pipes.clear();
        score = 0;
        updateScoreLabel();
        gameOverLabel.setVisible(false); // Saat permainan dimulai kembali, sembunyikan "Game Over"
        pressRLabel.setVisible(false);
        gameLoop.start();
        pipesCooldown.restart();
    }
}
