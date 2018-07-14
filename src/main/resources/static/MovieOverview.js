Vue.component('movie-overview', {
    template: `
    <div class="movie-item">
        <a :id="movie.imdbID" v-tooltip.hover="getTooltipText()">
            <img :src="movie.poster" @click="onMovieClicked()"/>
        </a>      
        <popover :target="'#'+movie.imdbID">
          <template slot="popover">
            <movie-details :movieId="movie.id"></movie-details>
          </template>
        </popover>                 
    </div>`,
    props: {
        movie: {
            type: Object
        }
    },
    data: function () {
        return {
        }
    },
    methods: {
        getTooltipText() {
          return  `Movie: ${this.movie.title}, Year:${this.movie.year}`;
        },
        onMovieClicked() {

        }
    }
});