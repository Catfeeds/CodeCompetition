export default {
    mounted(){
        window.addEventListener('resize',this.resizeChart)
    },
    destroyed(){
        window.removeEventListener('resize',this.resizeChart)
        if(this.chart){
            this.chart.dispose();
            this.chart = null;
        }
    },
    methods:{
        resizeChart(){
            if(this.chart){
                this.chart.resize();
            }
        }
    }
}