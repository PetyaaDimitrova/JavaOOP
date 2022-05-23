package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private Mission mission;
    private int n;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.mission = new MissionImpl();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                explorerRepository.add(explorer);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                explorerRepository.add(explorer);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                explorerRepository.add(explorer);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        List<String> list = Arrays.asList(exhibits);
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(list);
        stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State state = stateRepository.byName(stateName);
        if (explorerRepository.getCollection().size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        mission.explore(state, explorerRepository.getCollection());
        n++;
        int n = 0;
        for (Explorer e : explorerRepository.getCollection()
        ) {
            if (e.getEnergy() == 0) {
                n++;
            }
        }
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, n);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, n)).append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_EXPLORER_INFO).append(System.lineSeparator());
        List<Explorer> list = new ArrayList<>(explorerRepository.getCollection());
        for (int i = 0; i < list.size(); i++) {
            Explorer explorer = list.get(i);
            sb.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());
            List<String> items = (List<String>) explorer.getSuitcase().getExhibits();
            sb.append("Suitcase exhibits: ");
            if (items.isEmpty()) {
                sb.append("none").append(System.lineSeparator());
            } else {

                for (int j = 0; j < items.size(); j++) {
                    if (j == items.size() - 1) {
                        sb.append(items.get(j));
                        break;
                    }
                    sb.append(items.get(j)).append(", ");
                }
            }
            System.out.println();
        }

        return sb.toString().trim();
    }
}
