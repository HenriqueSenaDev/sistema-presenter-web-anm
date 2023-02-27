package gov.edu.anm.presenter.api.team;

import java.util.List;

import javax.transaction.Transactional;

import gov.edu.anm.presenter.api.avaliation.dtos.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public TeamOutputDto findById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));
        return new TeamOutputDto(team);
    }

    @Override
    public List<TeamOutputDto> findAll() {
        return teamRepository.findAll().stream().map(TeamOutputDto::new).toList();
    }

    @Override
    public List<TeamAvaliationOutputDto> findTeamAvaliations(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return team.getAvaliations().stream().map(TeamAvaliationOutputDto::new).toList();
    }

    @Override
    public Team updateTeam(TeamUpdateDto teamUpdateDto, Long id) {
        teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));

        Team teamToUpdate = new Team(teamUpdateDto);
        teamToUpdate.setId(id);
        return teamRepository.saveAndFlush(teamToUpdate);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
