Vue.component('movie-overview', {
    template: `
    <div class="movie-item">
        <a :id="movie.imdbID" v-tooltip.hover="getTooltipText()">
            <img :src="movie.poster" @click="onMovieClicked()"/>
        </a>      
        <popover :title="movie.title" :target="'#'+movie.imdbID">
          <template slot="popover">
            <h1>{{movie.year}}</h1>
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