<template>
    <div style="height:250px;position:relative;">
        <div class="chart-operate">
            <i class="el-icon-arrow-up" v-if="levelData.length>0" @click="onDrillUp" title="展示上一级"></i>  
            <i class="el-icon-arrow-down"  @click="onDrillDown" title="展示下一级"></i>
        </div>
        <div id="departmentChart1" style="height:250px;"></div>
    </div>
</template>

<script>
import echarts from 'echarts'
import mixinsChart from '../mixinChart'
import mixinResizeChart from '../mixinResizeChart'
import { mapGetters,mapActions,mapState } from 'vuex'

export default {
    props:['data'],
    mixins:[mixinsChart,mixinResizeChart],
    data(){
        return {
            chart:null
        }
    },
    mounted(){
        this.initChart();
    },
    watch:{
        chartData(data){
            var obj = {
                xData: [],
                yData:[]
            };
            for(var i=0;i<data.length;i++){
                obj.xData.push(data[i].name);
                obj.yData.push(data[i].value);
            }
            this.setOptions(obj);
        }
    },
    computed:{
        ...mapGetters(['getParams']),
        ...mapState({
            chartData:state=>state.reportDisplayStore.departmentStatisData
        })
    },
    methods:{
        ...mapActions(['getStatisByDepartment']),
        searchChartData(){
            var obj = this.getParams();
            //obj.id = this.levelData[this.levelData.length-1];
            this.getStatisByDepartment(obj)
        },
        initChart(){
            this.chart = echarts.init(document.getElementById('departmentChart1'));
        },
        setOptions(obj){
            var vm = this;
            var option = {
                title:{
                    show:true,
                    text:'各事业部人数',
                    left: 'center'
                },
                grid:{
                    top:40
                },
                tooltip: {
                    triggerOn: 'click',
                    show:true,
                    formatter:'{b0}: {c0}'
                },
                xAxis: {
                    type: 'category',
                    data: obj.xData,//['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                    axisLabel: {
                        interval: 0,
                        rotate: 50,
                        formatter(val){
                            return vm.splitStrDesc(val || '');
                        }
                    },
                    axisLine:{
                        show:false
                    },
                    splitLine:{
                        show:false
                    },
                    axisTick:{
                        show:false
                    },
                    axisPointer: {
                        value: this.gridIndex,
                        lineStyle: {
                            color: '#E6A23C',
                            opacity: 0.5,
                            width: 2
                        },
                        label: {
                            show: false,
                            backgroundColor: '#E6A23C'
                        },
                        handle: {
                            show: true,
                            color: '#E6A23C',
                            size:25,
                            margin:0
                        }
                    }
                },
                yAxis: {
                    type: 'value',
                    axisTick:{
                        show:false
                    },
                    axisLine:{
                        show:false
                    },
                    splitLine:{
                        lineStyle:{
                            type:"dotted"
                        }
                    }
                },
                series: [{
                    data: obj.yData,//[120, 200, 150, 80, 70, 110, 130],
                    type: 'line',
                    symbol: 'rect',
                    symbolSize: [32,20],
                    hoverAnimation:false,
                    lineStyle: {
                        normal: {
                            color: '#F7AA06',
                            width: 2
                        }
                    },
                    label:{
                        show:true ,
                        position:'inside'
                    },
                    itemStyle: {
                        normal: {
                            borderWidth: 0,
                            color: '#F55F02'
                        }
                    }
                }]
            };
            this.chart.setOption(option);
        }
    }
}
</script>

<style>

</style>
