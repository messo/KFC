package hu.sch.kfc.client.place;

import hu.sch.kfc.client.model.ProgramProxy;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ShowProgramPlace extends Place {

    private final ProgramProxy program;
    private final Long programId;

    public ShowProgramPlace(ProgramProxy program) {
        this.program = program;
        this.programId = program.getId();
    }

    public ShowProgramPlace(Long programId) {
        this.program = null;
        this.programId = programId;
    }

    public ProgramProxy getProgram() {
        return program;
    }

    public Long getProgramId() {
        return programId;
    }

    @Prefix(value = "showProgram")
    public static class Tokenizer implements PlaceTokenizer<ShowProgramPlace> {
        @Override
        public String getToken(ShowProgramPlace place) {
            return place.getProgramId().toString();
        }

        @Override
        public ShowProgramPlace getPlace(String token) {
            return new ShowProgramPlace(Long.valueOf(token));
        }
    }
}
