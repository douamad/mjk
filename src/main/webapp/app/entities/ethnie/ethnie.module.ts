import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { EthnieComponent } from './ethnie.component';
import { EthnieDetailComponent } from './ethnie-detail.component';
import { EthnieUpdateComponent } from './ethnie-update.component';
import { EthnieDeleteDialogComponent } from './ethnie-delete-dialog.component';
import { ethnieRoute } from './ethnie.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(ethnieRoute)],
  declarations: [EthnieComponent, EthnieDetailComponent, EthnieUpdateComponent, EthnieDeleteDialogComponent],
  entryComponents: [EthnieDeleteDialogComponent],
})
export class MjkEthnieModule {}
