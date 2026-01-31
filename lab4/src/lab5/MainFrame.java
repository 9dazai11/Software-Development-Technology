package lab5;

import lab4.AhoCorasick;
import lab4.Match;
import lab4.Trie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private final JTextArea textArea = new JTextArea(10, 50);
    private final JTextArea patternsArea = new JTextArea(10, 30);

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"pattern", "id", "start", "end"}, 0
    );
    private final JTable table = new JTable(model);

    private final JLabel statusLabel = new JLabel("Готово.");

    public MainFrame() {
        super("ЛР5 (Swing) — поиск шаблонов (Aho–Corasick, ЛР4)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocationRelativeTo(null);

        textArea.setLineWrap(true);
        patternsArea.setLineWrap(true);

        setLayout(new BorderLayout(10, 10));
        add(buildCenter(), BorderLayout.CENTER);
        add(buildBottom(), BorderLayout.SOUTH);

        // демо-данные
        textArea.setText("abccababcab");
        patternsArea.setText("ab\nabc\ncab");
    }

    private Component buildCenter() {
        JPanel left = new JPanel(new BorderLayout(6, 6));
        left.setBorder(BorderFactory.createTitledBorder("Текст"));
        left.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout(6, 6));
        right.setBorder(BorderFactory.createTitledBorder("Шаблоны (каждый с новой строки)"));
        right.add(new JScrollPane(patternsArea), BorderLayout.CENTER);

        JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        topSplit.setResizeWeight(0.68);

        JPanel tablePanel = new JPanel(new BorderLayout(6, 6));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Найденные совпадения"));
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplit, tablePanel);
        mainSplit.setResizeWeight(0.55);

        return mainSplit;
    }

    private Component buildBottom() {
        JPanel p = new JPanel(new BorderLayout());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton searchBtn = new JButton("Найти");
        JButton clearBtn = new JButton("Очистить");
        JButton demoBtn = new JButton("Демо");

        searchBtn.addActionListener(e -> onSearch());
        clearBtn.addActionListener(e -> onClear());
        demoBtn.addActionListener(e -> onDemo());

        buttons.add(searchBtn);
        buttons.add(clearBtn);
        buttons.add(demoBtn);

        JPanel status = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        status.add(statusLabel);

        p.add(buttons, BorderLayout.NORTH);
        p.add(status, BorderLayout.SOUTH);
        return p;
    }

    private void onSearch() {
        try {
            model.setRowCount(0);

            String text = textArea.getText();
            if (text == null) text = "";

            List<String> patterns = parsePatterns(patternsArea.getText());
            if (patterns.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Введите хотя бы один шаблон (непустая строка).",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // === Используем ЛР4 ===
            Trie trie = new Trie();
            for (String p : patterns) {
                trie.addPattern(p);
            }
            AhoCorasick ac = new AhoCorasick(trie);
            List<Match> matches = ac.search(text);

            for (Match m : matches) {
                model.addRow(new Object[]{m.pattern, m.patternId, m.start, m.end});
            }

            statusLabel.setText("Найдено совпадений: " + matches.size());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getClass().getSimpleName() + ": " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClear() {
        textArea.setText("");
        patternsArea.setText("");
        model.setRowCount(0);
        statusLabel.setText("Очищено.");
    }

    private void onDemo() {
        textArea.setText("abccababcab");
        patternsArea.setText("ab\nabc\ncab");
        model.setRowCount(0);
        statusLabel.setText("Загружены демо-данные.");
    }

    private List<String> parsePatterns(String raw) {
        List<String> res = new ArrayList<>();
        if (raw == null) return res;

        String[] lines = raw.split("\\R"); // любая новая строка
        for (String line : lines) {
            String s = line.trim();
            if (!s.isEmpty()) res.add(s);
        }
        return res;
    }
}
